package com.mbc.team.FAQ;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class FAQController {

	@Autowired
	SqlSession sqlSession;
	//경로 수정해야합니다.
	String path = "C:\\mbc\\Somall\\Somall\\baseballshop_project\\src\\main\\webapp\\image";
	//faqinput으로 이동
	@RequestMapping(value = "/faqin")
	public String faq() {
		
		return "faqinput";
	}
	//faqinput내용 저장
	@RequestMapping(value = "/faqsave", method = RequestMethod.POST)
	public String faq1(MultipartHttpServletRequest mul) throws IllegalStateException, IOException {
		String tab = mul.getParameter("tab");
		String title = mul.getParameter("title");
		String nickname = mul.getParameter("nickname");
		String fcontents = mul.getParameter("fcontents");
		MultipartFile fimg1 = mul.getFile("fimage1");
		MultipartFile fimg2 = mul.getFile("fimage2");
		MultipartFile fimg3 = mul.getFile("fimage3");
		
		String fname1 = fimg1.getOriginalFilename();
		String fname2 = fimg2.getOriginalFilename();
		String fname3 = fimg3.getOriginalFilename();
		FAQService fs = sqlSession.getMapper(FAQService.class);
		fimg1.transferTo(new File(path+"\\"+fname1));
		fimg2.transferTo(new File(path+"\\"+fname2));
		fimg3.transferTo(new File(path+"\\"+fname3));
		
		fs.faqinsert(tab,title,fcontents,nickname,fname1,fname2,fname3);
		
		return "redirect:/";
	}
	//저장내용 목록(+보드 미완)
	@RequestMapping(value = "/faqout")
	public String faq2(HttpServletRequest request, PageDTO dto ,Model mo) {
		String nowPage=request.getParameter("nowPage");
        String cntPerPage=request.getParameter("cntPerPage");
        
		FAQService fs = sqlSession.getMapper(FAQService.class);
		ArrayList<FAQDTO> list = fs.faqboard();
		mo.addAttribute("list", list);
		
		int total=fs.total();
        if(nowPage==null && cntPerPage == null) {
           nowPage="1";
           cntPerPage="5";
        }
        else if(nowPage==null) {
           nowPage="1";
        }
        else if(cntPerPage==null) {
           cntPerPage="5";
        }      
        dto=new PageDTO(total,Integer.parseInt(nowPage),Integer.parseInt(cntPerPage));
        mo.addAttribute("paging",dto);
        mo.addAttribute("list",fs.page(dto));
        
		return "faqoutput";
	}
	//저장내용 목록 상세페이지(+하단에 문의 답변내용 미완)
	@RequestMapping(value = "/faqdetail")
	public String faq3(Model mo, HttpServletRequest request) {
		int cnum = Integer.parseInt(request.getParameter("cnum"));
		
		FAQService fs = sqlSession.getMapper(FAQService.class);
		ArrayList<FAQDTO> list = fs.faqdetail(cnum);
		mo.addAttribute("list", list);
		
		return "faqDtailview";
	}
	//문의글 수정창
	@RequestMapping(value = "/faqupdate")
	public String faq4(HttpServletRequest request, Model mo) throws IllegalStateException, IOException {
		int cnum = Integer.parseInt(request.getParameter("cnum"));
		
		FAQService fs = sqlSession.getMapper(FAQService.class);
		
		FAQDTO dto = fs.faqupdate(cnum);
		mo.addAttribute("dto", dto);
		
		return "faqUpdateview";
	}
	//문의글 수정완료(+수정 완료됐다는 알림 필요)
	@RequestMapping(value = "/faqupdate2", method = RequestMethod.POST)
	public String faq5(MultipartHttpServletRequest mul) throws IllegalStateException, IOException {
		int cnum = Integer.parseInt(mul.getParameter("cnum"));
		String tab = mul.getParameter("tab");
		String title = mul.getParameter("title");
		String nickname = mul.getParameter("nickname");
		String fcontents = mul.getParameter("fcontents");
		MultipartFile fimg1 = mul.getFile("fimage1");
		MultipartFile fimg2 = mul.getFile("fimage2");
		MultipartFile fimg3 = mul.getFile("fimage3");
		
		String fname1 = fimg1.getOriginalFilename();
		String fname2 = fimg2.getOriginalFilename();
		String fname3 = fimg3.getOriginalFilename();
		FAQService fs = sqlSession.getMapper(FAQService.class);
		fimg1.transferTo(new File(path+"\\"+fname1));
		fimg2.transferTo(new File(path+"\\"+fname2));
		fimg3.transferTo(new File(path+"\\"+fname3));
		
		fs.faqupdate2(cnum,tab,title,fcontents,nickname,fname1,fname2,fname3);
		
		return "redirect:/faqout";
	}
	// 문의글 삭제
	@RequestMapping(value = "/faqdelete")
	public String faq6(HttpServletRequest request) {
	    int cnum = Integer.parseInt(request.getParameter("cnum"));
	    FAQService fs = sqlSession.getMapper(FAQService.class);
	    fs.faqdelete(cnum);
	    
	    return "redirect:/faqout";
	}
	// 문의글 게시판 문의 답변
		@RequestMapping(value = "/faqreply", method = RequestMethod.POST)
		public String faq7(HttpServletRequest request,Model mo) {
		    int cnum = Integer.parseInt(request.getParameter("cnum"));
		    FAQService fs = sqlSession.getMapper(FAQService.class);
		    ArrayList<FAQDTO> list = fs.faqreply(cnum);
		    mo.addAttribute("faqlist", list);
		    
		    return "faqreplyview";
	}
	
	//문의글 답변 내용 저장
		@RequestMapping(value = "/faqreplysave", method = RequestMethod.POST)
		public String faq8(HttpServletRequest request) throws IllegalStateException, IOException {
			int cnum = Integer.parseInt(request.getParameter("cnum"));
			String tab = request.getParameter("tab");
			String title = request.getParameter("title");
			String fcontents = request.getParameter("fcontents");
			String nickname = request.getParameter("nickname");
			int groups = Integer.parseInt(request.getParameter("groups"));
			int step = Integer.parseInt(request.getParameter("step"));
			int indent = Integer.parseInt(request.getParameter("indent"));
			
			FAQService fs = sqlSession.getMapper(FAQService.class);
			fs.faqstepup(groups,step);
			step++;
			indent++;;
			fs.faqreplysave(cnum,tab,title,fcontents,nickname,groups,step,indent);
			
			
			return "redirect:/faqout";
	}
		
	//문의글 목록 검색
		@RequestMapping(value = "/faqsearch", method = RequestMethod.POST)
		public String faq9(HttpServletRequest request, Model mo) throws IllegalStateException, IOException {
			
			String faqkey = request.getParameter("faqkey");
			String faqvalue = request.getParameter("faqvalue");
			
			FAQService fs = sqlSession.getMapper(FAQService.class);
			
			ArrayList<FAQDTO> list;
			if(faqkey.equals("title"))
			{
				list = fs.faqtitlesearch(faqvalue);
			}
			else
			{
				list = fs.faqnicknamesearch(faqvalue);
			}
			mo.addAttribute("list", list);
							
			return "faqoutput";
	}
		
	
		
	
		
		
		
}
