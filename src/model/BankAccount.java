package model;


import javax.swing.*;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BankAccount {
    private long amount;
    private final String accountName;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    LocalDateTime now;

    private final JTextArea notificationTA;
    private final JLabel amountLabel;

    public String getAccountName() {
        return accountName;
    }

    public BankAccount(long amount, String accountName, JTextArea notificationTA, JLabel amountLabel) {
        this.amount = amount;
        this.accountName = accountName;
        this.notificationTA = notificationTA;
        this.amountLabel = amountLabel;
        this.notificationTA.append(MessageFormat.format(">>>[Khởi tạo tài khoản {0} với số dư {1}]<<<\n\n", accountName, amount));
    }

    /**
     * Hàm kiểm tra số dư tài khoản
     *
     * @param withdrawAmount : số tiền cần rút
     * @return true nếu có thể rút tiền ngược lại false
     */
    private boolean checkAccountBalance(long withdrawAmount, String user) {

        // giả lập đọc database
        now = LocalDateTime.now();
        // giả lập đọc database
        this.notificationTA.append(
                MessageFormat.format(
                        "[{0}][{1}] Đang kiểm tra số dư...\n\n", dtf.format(now), user
                )
        );

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // nếu số dư lớn hơn hoặc bằng số tiền yêu cầu rút sẽ có thể rút
        return amount >= withdrawAmount;
    }

    /**
     * Hàm thực hiện rút tiền
     *
     * @param user           tên của người đang thực hiện rút tiền
     * @param withdrawAmount số tiền cần rút
     */
    private void withdraw(String user, long withdrawAmount) {
        // In thông tin người rút
        now = LocalDateTime.now();
        this.notificationTA.append(MessageFormat.format("[{0}][{1}][{2}] cần rút: {3}\n\n", dtf.format(now), user, this.amount, withdrawAmount));

        // giả lập xử lý kiểm tra số dư và thực hiện giao dịch

        if (checkAccountBalance(withdrawAmount, user)) {
            // giả lập thay đổi số dư trong database
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.amount -= withdrawAmount;
        } else {
            now = LocalDateTime.now();
            this.notificationTA.append(
                    MessageFormat.format(
                            "[{0}][{1}] không thể thực hiện giao dịch do số dư không đủ\n\n", dtf.format(now), user
                    )
            );
        }
        now = LocalDateTime.now();
        if (amountLabel != null) amountLabel.setText(MessageFormat.format("Số dư: {0}", amount));
        this.notificationTA.append(MessageFormat.format(
                        "[{0}][{1}] số dư: {2}\n\n", dtf.format(now), user, this.amount
                )
        );
    }


    private void deposit(String user, long depositAmount) {
        // in thông tin người rút
        now = LocalDateTime.now();
        this.notificationTA.append(MessageFormat.format("[{0}][{1}][{2}] đang nạp: {3}\n\n", dtf.format(now), user, this.amount, depositAmount));

        // giả lập đọc database và thay đổi số dư
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.amount += depositAmount;
        now = LocalDateTime.now();
        if (amountLabel != null) amountLabel.setText(MessageFormat.format("Số dư: {0}", amount));
        this.notificationTA.append(MessageFormat.format(
                        "[{0}][{1}] số dư: {2}\n\n", dtf.format(now), user, this.amount
                )
        );
        // gọi đối tượng đang chờ để thanh toán
        notify();
    }

    private void payWhenBalanceEnough(String user, long payAmount) {
        // In thông tin người rút
        now = LocalDateTime.now();
        this.notificationTA.append(MessageFormat.format("[{0}][{1}][{2}] cần thanh toán: {3}\n\n", dtf.format(now), user, this.amount, payAmount));

        while (!checkAccountBalance(payAmount, user)) {
            // Nếu không đủ tiền, thì đợi cho đến khi có đủ tiền thì thanh toán
            now = LocalDateTime.now();
            this.notificationTA.append(MessageFormat.format("[{0}][{1}] đang chờ đủ số dư để thực hiện thanh toán...\n\n", dtf.format(now), user));
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Đủ tiền, hoặc không còn đợi nữa, thì được phép rút
        // Giả lập thời gian rút tiền và
        // cập nhật số tiền còn lại vào database
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.amount -= payAmount;
        now = LocalDateTime.now();
        this.notificationTA.append(MessageFormat.format("[{0}][{1}][{2}] thanh toán: {3}\n\n", dtf.format(now), user, this.amount, payAmount));
        if (amountLabel != null) amountLabel.setText(MessageFormat.format("Số dư: {0}", amount));
        this.notificationTA.append(MessageFormat.format(
                        "[{0}][{1}] số dư: {2}\n\n", dtf.format(now), user, this.amount
                )
        );
    }

    public void deadlockTransfer(BankAccount toAccount, long transferAmount, String user) {
        synchronized (this) {
            // Rút tiền từ tài khoản này
            this.withdrawWithSync(user, transferAmount);
            // Nạp tiền vào toAccount
            toAccount.depositWithSync(toAccount.getAccountName(), transferAmount);
        }
    }

    /**
     * @param user
     * @param withdrawAmount
     */
    public void withdrawWithoutSync(String user, long withdrawAmount) {
        this.withdraw(user, withdrawAmount);
    }

    /**
     * @param user
     * @param withdrawAmount
     */
    public synchronized void withdrawWithSync(String user, long withdrawAmount) {
        this.withdraw(user, withdrawAmount);
    }

    /**
     * Hàm nạp tiền theo cơ chế đồng bộ hóa
     *
     * @param user          tên của người đang thực hiện rút tiền
     * @param depositAmount số tiền cần nạp
     */
    public synchronized void depositWithSync(String user, long depositAmount) {
        deposit(user, depositAmount);
    }

    /**
     * Đặt lệnh tự động thanh toán theo cơ chế đồng bộ hóa
     *
     * @param user      tên người đang đặt lệnh thanh toán
     * @param payAmount số tiền cần thanh toán
     */
    public synchronized void payWhenBalanceEnoughWithSync(String user, long payAmount) {
        payWhenBalanceEnough(user, payAmount);
    }


}
