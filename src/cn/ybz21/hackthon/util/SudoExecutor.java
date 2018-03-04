package cn.ybz21.hackthon.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * <p>
 * 这个类用于在Java程序中构建和执行Linux中的sudo命令。
 * 用法1：
 *    1. 执行buildCommands(...)方法构造sudo命令串。有两种方法可以构造sudo命令串：
 *
 *       若调用builderCommands(String cmd)方法构造sudo命令串，则此前应先修改/etc/sudoers文件，
 *       在其中添加一行：
 *          username  ALL=(ALL) NOPASSWD:ALL
 *       其中，"username"是需要运行这个程序的用户名。
 *
 *       若不想修改/etc/sudoers文件，则需要调用builderCommands(String cmd, String passwd)方法
 *       构造sudo命令串，
 *
 *       注意：无论使用哪种方法，形参cmd中均值包含需要以sudo方式执行的命令，不包含"sudo"命令本身。
 *
 *    2. 调用run(String[] cmds)执行由buildCommands返回的命令串数组。
 *
 * 用法2：
 *    1. 修改/etc/sudoers文件，在其中添加一行：
 *          username  ALL=(ALL) NOPASSWD:ALL
 *       其中，"username"是需要运行这个程序的用户名。
 *
 *    2. 调用run(String cmd)方法执行命令。
 *
 *    注意：形参cmd中仅包含需要以sudo方式执行的命令字符串，不要包含"sudo"命令本身。
 * </p>
 */

/**
 * @author kingfox
 *
 */
public class SudoExecutor {
	public static void run(String cmd) throws IOException, InterruptedException {
		String sudoCmd = "sudo " + cmd;
		System.out.println(sudoCmd);
		Process process = Runtime.getRuntime().exec(sudoCmd);
		InputStreamReader ir = new InputStreamReader(process.getInputStream());
		LineNumberReader input = new LineNumberReader(ir);
		String line;
		while ((line = input.readLine()) != null) {
			System.out.println(line);
		}
	}

	public static void run(String[] cmds) throws IOException,
			InterruptedException {
		// /* __debug_code__
		for (String cmd : cmds) {
			System.out.print(cmd);
			System.out.print(' ');
		}
		System.out.println();
		// */
		Process process = Runtime.getRuntime().exec(cmds);
		InputStreamReader ir = new InputStreamReader(process.getInputStream());
		LineNumberReader input = new LineNumberReader(ir);
		String line;
		while ((line = input.readLine()) != null) {
			System.out.println(line);
		}
	}

	/**
	 *
	 * @param cmd
	 * @return
	 */
	public static String[] buildCommands(String cmd) // to use this method, you
														// should modify
														// /etc/sudoers
	{
		String[] cmds = { shellName, shellParam, sudoCmd + " " + cmd };
		return cmds;
	}

	public static String[] buildCommands(String cmd, String sudoPasswd) {
		String[] cmds = { shellName, shellParam,
				"echo \"" + sudoPasswd + "\" | " + sudoCmd + " -S " + cmd };
		return cmds;
	}

	protected static String sudoCmd = "sudo";
	protected static String shellName = "/bin/bash";
	protected static String shellParam = "-c";

	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException,
			InterruptedException {
		// SudoExecutor se = new SudoExecutor();
		// se.chmod();
		
	}

	public void chmod(String path) throws IOException, InterruptedException {
		String cmd = "chmod -R 777 " + path;
		SudoExecutor.run(buildCommands(cmd, StaticValues.PWD));
	}

}
