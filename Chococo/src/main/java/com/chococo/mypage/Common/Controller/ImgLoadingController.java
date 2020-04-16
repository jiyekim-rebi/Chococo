package com.chococo.mypage.Common.Controller;

import java.io.File;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.coobird.thumbnailator.Thumbnails;

@Controller
@RequestMapping("/img/*")
public class ImgLoadingController {
	
	@Resource(name="uploadPath")
	private String uploadPath;

	@RequestMapping(value="/loading", method=RequestMethod.GET)
	protected void thumbnails(@RequestParam("imageFileName") String imageFileName, @RequestParam("mainCategory") int mainCategory,
			HttpServletResponse response) throws Exception {
		
		OutputStream out = response.getOutputStream();
		String filePath=uploadPath+"\\"+mainCategory+"\\"+imageFileName;
		File image = new File(filePath);

		if (image.exists()) {
			Thumbnails.of(image).size(600, 600).outputFormat("png").toOutputStream(out);
		}
		byte[] buffer = new byte[1024 * 8];
		out.write(buffer);
		
		out.flush();
		out.close();
	}
}
