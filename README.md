Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.hhhouse27:SSuperDialog:1.0.1'
	} 

/************************************************************************************************/
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
