package com.peng.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {

    //创建二维数组
    //用来管理数据，加载图片的时候会根据二维数组中的数据进行加载
    int[][] tem = new int[4][4];

    //记录空白方块在二维数组中的位置

    int x = 0;
    int y = 0;

    //定义一个变量，用来统计共用了多少步数
    int step = 0;

    //定义一个变量，用来记录选择了哪个类型的图片
    String typeImage = "animal";

    //定义一个变量，用来记录选择哪个图片
    String countImage = "animal3";

    //定义一个变量，用来展示当前图片的路径
    String path = "image\\" + typeImage + "\\" + countImage + "\\";

    //定义二维数组，存储正确的数据
    int[][] wim = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    //创建随机数
    Random random = new Random();

    //创建条目
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");

    JMenuItem belleItem = new JMenuItem("美女");
    JMenuItem animalItem = new JMenuItem("动物");
    JMenuItem sportsItem = new JMenuItem("运动");

    JMenuItem accountItem = new JMenuItem("公众号");

    public GameJFrame() {

        //初始化界面
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //初始化数据（打乱）
        initData();

        //初始化图片（根据打乱之后的结果去加载图片）
        initImage();

        //让界面显示
        this.setVisible(true);

    }

    private void initData() {
        //创建一维数组
        int[] temp = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        //遍历一维数组
        for (int i = 0; i < temp.length; i++) {
            int i1 = random.nextInt(temp.length);
            int tem = temp[i];
            temp[i] = temp[i1];
            temp[i1] = tem;
        }

        //遍历一维数组添加数据1
        for (int i = 0; i < temp.length; i++) {
            //判断空白方块的位置，并做好标记
            if (temp[i] == 0) {
                x = i / 4;
                y = i % 4;
            }
            tem[i / 4][i % 4] = temp[i];
        }

        //遍历二维数组添加数据2
        /*int index = 0;
        for (int i = 0; i < tem.length; i++) {
            for (int i1 = 0; i1 < tem[i].length; i1++) {
                tem[i][i1] = temp[index];
                index++;
            }
        }*/
    }

    //添加图片时，按照二维数组管理的数据进行添加
    private void initImage() {
        //清空原本已经出现的图片
        this.getContentPane().removeAll();

        //判断是否完成游戏
        if (victory()) {
            //显示胜利图标
            JLabel wim = new JLabel(new ImageIcon("image\\win.png"));
            wim.setBounds(203, 283, 197, 73);
            this.getContentPane().add(wim);
        }

        //添加计步器
        JLabel stepCount = new JLabel("当前的步数:" + step);
        stepCount.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepCount);

        //细节：先加载的图片在上面，后加载的图片在下面

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                //获取当前要加载图片的序号
                int number = tem[j][i];
                //创建一个图片ImageIcon的对象
                ImageIcon icon = new ImageIcon(path + number + ".jpg");
                //创建一个JLabel对象（管理容器）
                JLabel jLabel = new JLabel(icon);
                //指定图片的位置
                jLabel.setBounds(105 * i + 83, 105 * j + 134, 105, 105);
                //给图片添加边框
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                //把管理容器添加到界面中
                this.getContentPane().add(jLabel);
            }
        }

        //背景图片
        ImageIcon imageIcon = new ImageIcon("image\\background.png");
        JLabel background = new JLabel(imageIcon);
        background.setBounds(40, 40, 508, 560);
        this.getContentPane().add(background);

        //刷新界面
        this.getContentPane().repaint();
    }

    private void initJMenuBar() {
        //初始化菜单
        JMenuBar jMenuBar = new JMenuBar();
        //创建菜单选项
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");
        JMenu replaceJMenu = new JMenu("更换图片");

        //功能添加到选项
        functionJMenu.add(replaceJMenu);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        //关于我们添加到选项
        aboutJMenu.add(accountItem);

        //更换图片添加到选项
        replaceJMenu.add(belleItem);
        replaceJMenu.add(animalItem);
        replaceJMenu.add(sportsItem);

        //添加到菜单
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //给条目绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        belleItem.addActionListener(this);
        animalItem.addActionListener(this);
        sportsItem.addActionListener(this);

        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        //设置界面的宽高
        this.setSize(603, 680);
        //设置界面的标题
        this.setTitle("拼图小游戏");
        //设置页面置顶
        this.setAlwaysOnTop(true);
        //设置页面居中
        this.setLocationRelativeTo(null);
        //设置窗口关闭
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //取消默认的居中放置，只有取消了才会按照XY轴的形式添加组件
        this.setLayout(null);
        //给整个界面添加键盘监听事件
        this.addKeyListener(this);
    }

    //键盘类型
    @Override
    public void keyTyped(KeyEvent e) {

    }

    //键盘按下不松
    @Override
    public void keyPressed(KeyEvent e) {
        //判断游戏是否胜利，胜利后无法查看完整图片
        if (victory()) {
            return;
        }

        int keyCode = e.getKeyCode();
        //查看完整图片
        if (keyCode == 65) {
            //把界面中的所有图片删除
            this.getContentPane().removeAll();

            //重新加载计步器
            JLabel stepCount = new JLabel("当前的步数:" + step);
            stepCount.setBounds(50, 30, 100, 20);
            this.getContentPane().add(stepCount);

            //加载第一张完整的图片
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);

            //加载背景图片
            ImageIcon imageIcon = new ImageIcon("image\\background.png");
            JLabel background = new JLabel(imageIcon);
            background.setBounds(40, 40, 508, 560);
            this.getContentPane().add(background);

            //刷新界面
            this.getContentPane().repaint();
        }
    }

    //键盘释放
    @Override
    public void keyReleased(KeyEvent e) {
        //判断游戏是否胜利
        if (victory()) {
            //1.返回结果。2.结束方法。
            return;
        }

        //游戏未胜利
        //对上，下，左，右进行判断
        //左:37,上:38,右:39,下:40
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case 37: {
                //向左移动

                //判断y值是否越界，越界后不执行方法
                if (y == 0) {
                    return;
                }
                tem[x][y] = tem[x][y - 1];
                tem[x][y - 1] = 0;
                y--;
                //移动一次计数器自增一次
                step++;
                //调用方法重新加载图片
                initImage();
                break;
            }
            case 38: {
                //向上移动

                //判断x值是否越界，越界后不执行方法
                if (x == 0) {
                    return;
                }
                tem[x][y] = tem[x - 1][y];
                tem[x - 1][y] = 0;
                x--;
                //移动一次计数器自增一次
                step++;
                //调用方法重新加载图片
                initImage();
                break;
            }
            case 39: {
                //向右移动

                if (y >= 3) {
                    return;
                }
                tem[x][y] = tem[x][y + 1];
                tem[x][y + 1] = 0;
                y++;
                //移动一次计数器自增一次
                step++;
                //调用方法重新加载图片
                initImage();
                break;
            }
            case 40: {
                //向下移动

                //判断x值是否越界，越界后不执行方法
                if (x >= 3) {
                    return;
                }
                tem[x][y] = tem[x + 1][y];
                tem[x + 1][y] = 0;
                x++;
                //移动一次计数器自增一次
                step++;
                //调用方法重新加载图片
                initImage();
                break;
            }
            case 65:
                //返回初始图片
                initImage();
                break;

            case 87: {
                //一键完成
                tem = new int[][]{
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12},
                        {13, 14, 15, 0},
                };
                initImage();
                break;
            }
        }
    }

    //定义方法，判断是否胜利(判断tem中的数据是否与wim中的数据相同)
    public Boolean victory() {
        for (int i = 0; i < tem.length; i++) {
            for (int i1 = 0; i1 < tem[i].length; i1++) {
                //有一个数据不一样，返回false
                if (tem[i][i1] != wim[i][i1]) {
                    return false;
                }
            }
        }
        //循环结束后，数组遍历比较完毕，数据都相同，返回true
        return true;
    }

    //点击执行
    @Override
    public void actionPerformed(ActionEvent e) {
        //获取当前被点击的条目对象
        Object source = e.getSource();
        if (source == replayItem) {
            //重新开始游戏

            //再次打乱二维数组中的数据
            initData();
            //计步器清零
            step = 0;
            //重新加载图片
            initImage();
        } else if (source == reLoginItem) {
            //重新登录
            this.setVisible(false);
            new LoginJFrame();
        } else if (source == closeItem) {
            //关闭游戏
            System.exit(0);
        } else if (source == accountItem) {
            //公众号
            //创建弹窗对象
            JDialog jDialog = new JDialog();
            //创建管理容器的图片对象
            JLabel jLabel = new JLabel(new ImageIcon("image\\about.png"));
            //相对弹框设置位置和宽高
            jLabel.setBounds(0, 0, 258, 258);
            //把图片添加到弹窗中
            jDialog.getContentPane().add(jLabel);
            //给弹框设置大小
            jDialog.setSize(344, 344);
            //设置弹框置顶
            jDialog.setAlwaysOnTop(true);
            //设置弹框居中
            jDialog.setLocationRelativeTo(null);
            //弹框不关闭则无法操作下面的界面
            jDialog.setModal(true);
            //让弹框显示
            jDialog.setVisible(true);
        } else if (source == belleItem) {
            //美女
            //更换美女
            girl();
            //打乱图片
            initData();
            //初始化图片
            initImage();

        } else if (source == animalItem) {
            //动物
            //更换动物
            animal();
            //打乱图片
            initData();
            //初始化图片
            initImage();

        } else if (source == sportsItem) {
            //运动
            //更换运动
            sport();
            //打乱图片
            initData();
            //初始化图片
            initImage();
        }
    }

    private void girl() {
        //更改图片类型为美女
        typeImage = "girl";
        //获取对应的索引
        int i = random.nextInt(13) + 1;
        //拼接字符串
        countImage = "girl" + i;
        path = "image\\" + typeImage + "\\" + countImage + "\\";
    }

    private void sport() {
        //更改图片类型为运动
        typeImage = "sport";
        //获取对应的索引
        int i = random.nextInt(10) + 1;
        //拼接字符串
        countImage = "sport" + i;
        path = "image\\" + typeImage + "\\" + countImage + "\\";
    }

    private void animal() {
        //更改图片类型为动物
        typeImage = "animal";
        //获取对应的索引
        int i = random.nextInt(8) + 1;
        //拼接字符串
        countImage = "animal" + i;
        path = "image\\" + typeImage + "\\" + countImage + "\\";
    }

}
