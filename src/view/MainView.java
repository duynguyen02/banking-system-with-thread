package view;


import model.BankAccount;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;


public class MainView extends JFrame implements ActionListener {

    private static final String PROJECT_NAME = "Các vấn đề về luồng thông qua việc mô phỏng Hệ Thống Giao Dịch Ngân Hàng";
    private final JLabel projectName = new JLabel();
    JButton nonSyncBtn = new JButton("Bất đồng bộ");
    JButton mutualExclusiveBtn = new JButton("Mutual Cooperation");
    JButton cooperationBtn = new JButton("Cooperation");
    JButton deadLockBtn = new JButton("Deadlock");
    JButton stopThreadBtn = new JButton("Dừng luồng");
    JButton statusThreadBtn = new JButton("Kiểm tra luồng");
    JLabel amountLabel = new JLabel("Số dư: 0");
    JTextArea notificationArea = new JTextArea();

    JLabel initAmount = new JLabel("Khởi tạo số dư: ");
    JTextField initAmountTF = new JTextField();

    JLabel user1Withdraw = new JLabel("Số tiền User1 rút: ");
    JTextField user1WithdrawTF = new JTextField();

    JLabel user2Withdraw = new JLabel("Số tiền User2 rút: ");
    JTextField user2WithdrawTF = new JTextField();
    JLabel user3Transfer = new JLabel("Số tiền User3 chuyển: ");
    JTextField user3TransferTF = new JTextField();

    JLabel user2Transfer = new JLabel("Số tiền User2 chuyển: ");
    JTextField user2TransferTF = new JTextField();

    JLabel user2Deposit = new JLabel("Số tiền User2 nạp: ");
    JTextField user2DepositTF = new JTextField();

    public MainView() {
        super(PROJECT_NAME);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setSize(800, 800);
        this.setResizable(false);

        init();


        this.setVisible(true);
    }

    private void init() {
//        notificationArea.setBounds(0, 141, 800, 350);
//        notificationArea.setFont(new Font(null, Font.BOLD, 15));
//        notificationArea.setEditable(false);
//        this.add(notificationArea);
        /// Dòng 1 ///
        projectName.setText(PROJECT_NAME);
        projectName.setFont(new Font(null, Font.BOLD, 15));
        projectName.setBounds(0, 0, 800, 50);
        projectName.setBackground(Color.PINK);
        projectName.setHorizontalAlignment(SwingConstants.CENTER);
        projectName.setVerticalAlignment(SwingConstants.CENTER);
        projectName.setOpaque(true);
        this.add(projectName);

        /// Dòng 2 ///
        amountLabel.setFont(new Font(null, Font.BOLD, 15));
        amountLabel.setBounds(0, 51, 800, 30);
        amountLabel.setBackground(Color.PINK);
        amountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        amountLabel.setVerticalAlignment(SwingConstants.CENTER);
        amountLabel.setOpaque(true);
        this.add(amountLabel);

        /// Dòng 3 ///
        nonSyncBtn.setBounds(0, 81, 200, 30);
        nonSyncBtn.setFocusPainted(false);
        nonSyncBtn.addActionListener(this);
        this.add(nonSyncBtn);

        mutualExclusiveBtn.setBounds(201, 81, 200, 30);
        mutualExclusiveBtn.setFocusPainted(false);
        mutualExclusiveBtn.addActionListener(this);
        this.add(mutualExclusiveBtn);

        cooperationBtn.setBounds(401, 81, 200, 30);
        cooperationBtn.setFocusPainted(false);
        cooperationBtn.addActionListener(this);
        this.add(cooperationBtn);

        deadLockBtn.setBounds(601, 81, 200, 30);
        deadLockBtn.setFocusPainted(false);
        deadLockBtn.addActionListener(this);
        this.add(deadLockBtn);

        /// Dòng 4 ///
        stopThreadBtn.setBounds(0, 111, 200, 30);
        stopThreadBtn.setFocusPainted(false);
        stopThreadBtn.addActionListener(this);
        this.add(stopThreadBtn);
        statusThreadBtn.setBounds(201, 111, 200, 30);
        statusThreadBtn.setFocusPainted(false);
        statusThreadBtn.addActionListener(this);
        this.add(statusThreadBtn);

        /// Dòng 5 ///
        initAmount.setBounds(0, 141, 200, 30);
        initAmount.setHorizontalAlignment(SwingConstants.CENTER);
        initAmount.setVerticalAlignment(SwingConstants.CENTER);
        this.add(initAmount);
        initAmountTF.setBounds(201, 141, 200, 30);
        initAmountTF.setText("10000000");
        this.add(initAmountTF);

        user2Transfer.setBounds(401, 141, 200, 30);
        user2Transfer.setHorizontalAlignment(SwingConstants.CENTER);
        user2Transfer.setVerticalAlignment(SwingConstants.CENTER);
        this.add(user2Transfer);
        user2TransferTF.setBounds(601, 141, 200, 30);
        user2TransferTF.setText("10000000");
        this.add(user2TransferTF);

        /// Dòng 6 ///
        user1Withdraw.setBounds(0, 171, 200, 30);
        user1Withdraw.setHorizontalAlignment(SwingConstants.CENTER);
        user1Withdraw.setVerticalAlignment(SwingConstants.CENTER);
        this.add(user1Withdraw);
        user1WithdrawTF.setBounds(201, 171, 200, 30);
        user1WithdrawTF.setText("8000000");
        this.add(user1WithdrawTF);

        user2Withdraw.setBounds(401, 171, 200, 30);
        user2Withdraw.setHorizontalAlignment(SwingConstants.CENTER);
        user2Withdraw.setVerticalAlignment(SwingConstants.CENTER);
        this.add(user2Withdraw);
        user2WithdrawTF.setBounds(601, 171, 200, 30);
        user2WithdrawTF.setText("10000000");
        this.add(user2WithdrawTF);

        user3Transfer.setBounds(0, 201, 200, 30);
        user3Transfer.setHorizontalAlignment(SwingConstants.CENTER);
        user3Transfer.setVerticalAlignment(SwingConstants.CENTER);
        this.add(user3Transfer);
        user3TransferTF.setBounds(201, 201, 200, 30);
        user3TransferTF.setText("3000000");
        this.add(user3TransferTF);

        user2Deposit.setBounds(401, 201, 200, 30);
        user2Deposit.setHorizontalAlignment(SwingConstants.CENTER);
        user2Deposit.setVerticalAlignment(SwingConstants.CENTER);
        this.add(user2Deposit);
        user2DepositTF.setBounds(601, 201, 200, 30);
        user2DepositTF.setText("5000000");
        this.add(user2DepositTF);

        notificationArea.setBounds(0, 261, 800, 540);
        notificationArea.setFont(new Font(null, Font.BOLD, 15));
        notificationArea.setEditable(false);
        this.add(notificationArea);
    }

    private BankAccount bankAccount1;
    private BankAccount bankAccount2;

    private Thread thread1 = null;
    private Thread thread2 = null;

    @Override
    public void actionPerformed(ActionEvent e) {

        long amount;
        long user1Withdraw;
        long user2Withdraw;
        long user2Deposit ;
        long user2Transfer;
        long user3Transfer;
        try {
            amount = Long.parseLong(initAmountTF.getText());
            user1Withdraw = Long.parseLong(user1WithdrawTF.getText());
            user2Withdraw = Long.parseLong(user2WithdrawTF.getText());
            user2Deposit = Long.parseLong(user2DepositTF.getText());
            user2Transfer = Long.parseLong(user2TransferTF.getText());
            user3Transfer = Long.parseLong(user3TransferTF.getText());
        } catch (Exception err) {
            JOptionPane.showMessageDialog(this,
                    "Bạn cần nhập thông số hợp lệ!",
                    "Cảnh bảo",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }


        // nếu nút này được chọn thì chạy luồng bất đồng bộ
        if (e.getSource() == nonSyncBtn) {
            this.notificationArea.setText("");
            amountLabel.setText(MessageFormat.format("Số dư: {0}", amount));
            bankAccount1 = new BankAccount(amount, "1", notificationArea, amountLabel);
            thread1 = new Thread() {
                @Override
                public void run() {
                    super.run();
                    bankAccount1.withdrawWithoutSync("USER1", user1Withdraw);
                }
            };

            thread2 = new Thread() {
                @Override
                public void run() {
                    super.run();
                    bankAccount1.withdrawWithoutSync("USER2", user2Withdraw);
                }
            };

            thread1.start();
            thread2.start();

        }

        if (e.getSource() == cooperationBtn) {
            this.notificationArea.setText("");
            amountLabel.setText(MessageFormat.format("Số dư: {0}", amount));
            bankAccount1 = new BankAccount(amount, "1", notificationArea, amountLabel);
            thread1 = new Thread() {
                @Override
                public void run() {
                    super.run();
                    bankAccount1.payWhenBalanceEnoughWithSync("USER1", user1Withdraw);
                }
            };


            thread2 = new Thread() {
                @Override
                public void run() {
                    super.run();
                    bankAccount1.depositWithSync("USER2", user2Deposit);
                }
            };

            thread1.start();
            thread2.start();
        }


        if (e.getSource() == deadLockBtn) {
            this.notificationArea.setText("");
            amountLabel.setText(MessageFormat.format("Số dư: {0}", amount));
            bankAccount1 = new BankAccount(amount, "1", notificationArea, amountLabel);
            bankAccount2 = new BankAccount(amount, "2", notificationArea, null);
            thread1 = new Thread() {
                @Override
                public void run() {
                    super.run();
                    bankAccount1.deadlockTransfer(bankAccount2, user2Transfer, "USER2");
                }
            };

            thread2 = new Thread() {
                @Override
                public void run() {
                    super.run();
                    bankAccount2.deadlockTransfer(bankAccount1, user3Transfer, "USER3");
                }
            };

            thread1.start();
            thread2.start();
        }

        if (e.getSource() == stopThreadBtn) {
            if (thread1 == null && thread2 == null) {
                JOptionPane.showMessageDialog(this,
                        "Chưa khỏi tạo luồng!",
                        "Cảnh bảo",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
//            assert thread1 != null;
//            if (thread1.isAlive()) {
//                thread1.interrupt();
//            }
//            if (thread2.isAlive()) {
//                thread2.interrupt();
//            }
            this.notificationArea.setText("");
            thread2 = null;
            thread1 = null;
        }



        if (e.getSource() == mutualExclusiveBtn) {
            this.notificationArea.setText("");
            amountLabel.setText(MessageFormat.format("Số dư: {0}", amount));
            bankAccount1 = new BankAccount(amount, "1", notificationArea, amountLabel);
            thread1 = new Thread() {
                @Override
                public void run() {
                    super.run();
                    bankAccount1.withdrawWithSync("USER1", user1Withdraw);
                }
            };

            thread2 = new Thread() {
                @Override
                public void run() {
                    super.run();
                    bankAccount1.withdrawWithSync("USER2", user2Withdraw);
                }
            };

            thread1.start();
            thread2.start();

        }



        if (e.getSource() == statusThreadBtn) {
            if (thread1 == null && thread2 == null) {
                JOptionPane.showMessageDialog(this,
                        "Chưa khỏi tạo luồng!",
                        "Cảnh bảo",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            assert thread1 != null;
            if (thread1.isAlive() && thread2.isAlive()) {
                JOptionPane.showMessageDialog(this,
                        "Cả 2 luồng đang chạy!",
                        "Cảnh bảo",
                        JOptionPane.WARNING_MESSAGE);

            } else if (thread1.isAlive()) {
                JOptionPane.showMessageDialog(this,
                        "Cả luồng 1 đang chạy!",
                        "Cảnh bảo",
                        JOptionPane.WARNING_MESSAGE);
            } else if (thread2.isAlive()) {
                JOptionPane.showMessageDialog(this,
                        "Cả luồng 2 đang chạy!",
                        "Cảnh bảo",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Không có luồng nào đang chạy!",
                        "Cảnh bảo",
                        JOptionPane.WARNING_MESSAGE);
            }
        }




    }
}
