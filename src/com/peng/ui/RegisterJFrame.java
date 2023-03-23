package com.peng.ui;

import javax.swing.*;

public class RegisterJFrame extends JFrame {
    public RegisterJFrame() {
        this.setSize(488, 500);
        //设置界面的标题
        this.setTitle("拼图注册");
        //设置页面置顶
        this.setAlwaysOnTop(true);
        //设置页面居中
        this.setLocationRelativeTo(null);
        //让界面显示
        this.setVisible(true);
        //设置窗口关闭
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
