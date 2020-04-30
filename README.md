Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.hhhouse27:SSuperDialog:1.1.0'
	} 


方法一
new SSuperDialog.Builder(this, SSuperDialog.TYPE_CENTER)
                .setTitleAndColor("这是标题", Color.GREEN)
                .setContent("这个对话框可设置文本、文本颜色、按钮点击事件、弹出动画")
                .setLeftBtn("关闭", ContextCompat.getColor(this, R.color.colorAccent), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "点击事件自定义，对话框关闭固定执行001", Toast.LENGTH_SHORT).show();
                    }
                })
                .setRightBtn("确定", Color.parseColor("#15B6FF"), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "点击事件自定义，对话框关闭固定执行002", Toast.LENGTH_SHORT).show();
                    }
                })
                .setDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        Toast.makeText(MainActivity.this, "对话框关闭了！！！", Toast.LENGTH_SHORT).show();
                    }
                })
                .build().show();
		
方法二
SpannableString spannableString = new SpannableString("如何获得淘宝订单号？点击查看");
        //设置颜色
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FC6565")), 10, 14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        new SingleBtnDialog.Builder(this, true)
                .setTitleAndColor("这是标题", Color.GREEN)
                .setContentALL(spannableString, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "您点击了查看！", Toast.LENGTH_SHORT).show();
                    }
                })
                .setShowETV(true, "请输入文本...")
                .setSureBtnBg(Color.GRAY)
                .setSureBtn("立即抽奖", Color.WHITE, new SingleBtnDialog.SureBtnClick(true) {
                    @Override
                    public void onClick(View v, String editTextStr) {
                        Toast.makeText(MainActivity.this, "您输入的内容是：" + editTextStr, Toast.LENGTH_SHORT).show();
                    }
                })
                .setDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        Toast.makeText(MainActivity.this, "对话框关闭了！！！", Toast.LENGTH_SHORT).show();
                    }
                })
                .build().show();
		
方法三
final ProgressDialog dialog = new ProgressDialog.Builder(this)
                .setContent("正在下载视频...")
                .setMainColor(Color.parseColor("#FF0000"))
                .setDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        Toast.makeText(MainActivity.this, "下载完成！", Toast.LENGTH_SHORT).show();
                    }
                }).build();
        dialog.show();
       new Thread(new Runnable() {
            @Override
            public void run() {
                int progress = 0;//0-360
                while (true) {
                    try {
                        Thread.sleep(100);
                        progress++;
                        final int finalProgress = progress;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialog.downloadProcess(finalProgress);
                            }
                        });
                        if (progress == 100) {
                            dialog.dismiss();
                            break;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
	
方法四
View view1 = LayoutInflater.from(this).inflate(R.layout.view_custom,null);
        new CustomViewDialog.Builder(this)
                .setCustomView(view1)
                .setTitleAndColor("检测到商品", Color.GREEN)
                .setDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        Toast.makeText(MainActivity.this, "对话框关闭了！！！", Toast.LENGTH_SHORT).show();
                    }
                })
                .setSureBtnBg(Color.GRAY)
                .setSureBtn("查看商品", Color.WHITE, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "跳转至商品详情页" , Toast.LENGTH_SHORT).show();
                    }
                })
                .setSureBtnBg2(Color.parseColor("#aaaaaa"))
                .setSureBtn2("取消", Color.parseColor("#ffffff"), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "点击了取消!!!", Toast.LENGTH_SHORT).show();
                    }
                })
                .build().show();
		
		
		
