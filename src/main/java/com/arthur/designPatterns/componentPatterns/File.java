package com.arthur.designPatterns.componentPatterns;

/**
 * Created by wangtao on 17/4/12.
 */
//文件
public class File implements IFile{

	private String name;
	private IFile folder;

	public File(String name,IFile folder) {
		super();
		this.name = name;
		this.folder = folder;
	}

	public String getName() {
		return name;
	}

	public void delete() {
		folder.deleteFile(name);
		System.out.println("---删除[" + name + "]---");
	}

	//文件不支持创建新文件
	public void createNewFile(String name) {
		throw new UnsupportedOperationException();
	}
	//文件不支持删除文件
	public void deleteFile(String name) {
		throw new UnsupportedOperationException();
	}
	//文件不支持获取下面的文件列表
	public IFile getIFile(int index) {
		throw new UnsupportedOperationException();
	}

}
