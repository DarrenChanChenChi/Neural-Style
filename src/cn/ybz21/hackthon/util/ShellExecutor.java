package cn.ybz21.hackthon.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ShellExecutor {

	

	public ShellExecutor() {
	
	}

	// 本地执行方法
	public static void execLocal(String cmd) {
		try {
			Process proc = Runtime.getRuntime().exec(cmd);
			GobblerThread errorGobbler = new GobblerThread(
					proc.getErrorStream(), "ERROR");
			GobblerThread outputGobbler = new GobblerThread(
					proc.getInputStream(), "OUTPUT");
			errorGobbler.start();
			outputGobbler.start();
			int exitVal = proc.waitFor();
			System.out.println("ExitValue: " + exitVal);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String args[]) throws Exception {
	
		execLocal("sh /home/ybz/aa.sh");
	}
}

class GobblerThread extends Thread {
	InputStream is;
	String type;

	GobblerThread(InputStream is, String type) {
		this.is = is;
		this.type = type;
	}

	public void run() {
		try {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null)
				System.out.println(line);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}