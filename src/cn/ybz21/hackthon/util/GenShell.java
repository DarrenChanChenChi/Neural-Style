package cn.ybz21.hackthon.util;

public class GenShell {
	public void genShells(String sourcePath, String[] targetPaths, String name) {
		String str = "#!/bin/bash";
		String str2 = "catkin_make";
		String str3 = "source ./devel/setup.bash";
		String str4 = "rosrun  talker.py";
		String str5 = "rosrun  listener.py";
		String strs[] = { str, str2, str3, str4,str5 };
		FileUtil.writeFile(strs, StaticValues.PATH_SHELL, name + ".sh");
	}
}
