package org.noka.print;

import javax.servlet.http.HttpServletRequest;

import org.apache.fop.apps.MimeConstants;

/**
 * 打印生成接口
 * @author rebin
 *
 */
public interface PrintUtil {
	public static final String[] TYPES=new String[]{MimeConstants.MIME_PDF,MimeConstants.MIME_RTF,MimeConstants.MIME_PNG,MimeConstants.MIME_SVG};
	public static final String[] FILEEXT=new String[]{"pdf","rtf","png","xml"};
	//-----------------------------------------------------
	public String GetPrint(HttpServletRequest request);
}
