package cn.ybz21.hackthon.action;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import cn.ybz21.hackthon.bean.ResultCode;
import cn.ybz21.hackthon.bean.UploadBean;
import cn.ybz21.hackthon.util.DateUtil;
import cn.ybz21.hackthon.util.FileUtil;
import cn.ybz21.hackthon.util.ResponseUtil;
import cn.ybz21.hackthon.util.StaticValues;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class UPloadAction extends ActionSupport implements 	ServletResponseAware {
	/**
	 * 
	 */

	private static final long serialVersionUID = 1409037081277613450L;
	private List<File> files;
	private List<String> uploadPaths;

	private HttpServletResponse response;

	public void doAction() {
		System.out.println("上传的文件=" + files.size());


		for (File f : files)
			System.out.println(f.getName());
		for (String p : uploadPaths)
			System.out.println(p);

		String attachId = saveFiles();
		ResultCode resultCode = new ResultCode();
		resultCode.result=1;
		Gson gson = new Gson();
		String jsonData = gson.toJson(resultCode);
		new ResponseUtil().Response(response, jsonData);
		//TODO 
//		genShell();
//		runShell();
	}

	String saveFiles() {
		String pathsReal[] = new String[files.size()];

		for (int i = 0; i < files.size(); i++) {
			String oldPath = files.get(i).getPath();
			UUID uuid = UUID.randomUUID();
			String name = new DateUtil().getNowFormatDate("yyyy-MM-dd-HHmmss")
					+ "_" + uuid.toString() + "."
					+ FileUtil.getExtensionName(uploadPaths.get(i));
			pathsReal[i] = StaticValues.PATH_ATTACH + "/" + name;
			
			System.out.println(pathsReal[i]);
			FileUtil.copyFile(oldPath, pathsReal[i]);
			
		}

		return null;

	}

	public List<String> getUploadPaths() {
		return uploadPaths;
	}

	public void setUploadPaths(List<String> uploadPaths) {
		this.uploadPaths = uploadPaths;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	
	public HttpServletResponse getServletResponse() {
		return response;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

}