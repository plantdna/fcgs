package com.pids.core.utils;

import com.pids.core.exception.ICoreException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

import java.io.File;

/**
 * 利用Ant.jar进行zip压缩与解压缩，不支持目录内包含空格
 * @author jiangbin
 * @date 2013年12月25日下午12:34:55
 */
public class ZipUtils {

	/**
	 * 压缩目录
	 * @author jiangbin
	 * @param sourceDir 源目录，注意：必需是目录，否则会抛出异常
	 * @param targetZip 目标zip
	 * @throws Exception 
	 * @date 2013年12月25日下午12:32:47
	 */
	public static void zip(String sourceDir, String targetZip) throws ICoreException {
		Project project = new Project();
		Zip zip = new Zip();
		zip.setProject(project);
		zip.setDestFile(new File(targetZip));//设置生成的目标zip文件File对象
		FileSet fileSet = new FileSet();
		fileSet.setProject(project);
		fileSet.setDir(new File(sourceDir));//设置将要进行压缩的源文件File对象
		zip.addFileset(fileSet);
		zip.execute();
	}

	/**
	 * 解压缩
	 * @author jiangbin
	 * @param sourceZip 源zip文件
	 * @param destDir 目标目录
	 * @throws Exception
	 * @date 2013年12月25日下午12:33:51
	 */
	public static void unzip(String sourceZip, String destDir) throws ICoreException {
		Project project = new Project();
		Expand expand = new Expand();
		expand.setProject(project);
		expand.setSrc(new File(sourceZip));
		expand.setOverwrite(false);//是否覆盖
		File f = new File(destDir);
		expand.setDest(f);
		expand.execute();
	}

}
