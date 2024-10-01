package com.mbc.team.board;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class BoardController {

	String savepath="C://project//teamproject//baseballshop_project//src//main//webapp//image";
	@Autowired
	SqlSession sqlsession;
	
	@RequestMapping(value = "/gongjiinput", method = RequestMethod.GET)
	public String gongjiinput() {
		return "gongjiinput";
	}
	
	@RequestMapping(value = "/gongjisave", method = RequestMethod.POST)
	public String gongjisave(MultipartHttpServletRequest mul) throws IllegalStateException, IOException {
		String id=mul.getParameter("id");
		String nickname=mul.getParameter("nickname");
		String gtitle=mul.getParameter("gtitle");
		String gcontents=mul.getParameter("gcontents");
		MultipartFile mf=mul.getFile("gimage");
		String gimagefn=mf.getOriginalFilename();
		mf.transferTo(new File(savepath+"//"+gimagefn));
		BoardService bs=sqlsession.getMapper(BoardService.class);
		bs.insertgongji(id, nickname, gtitle, gcontents, gimagefn);
		return "redirect:/gongjiboard";
	}
	
	@RequestMapping(value = "/gongjiboard", method = RequestMethod.GET)
	public String gongjiboard(Model mo, PageDTO dto, HttpServletRequest request) {
		String nowPage=request.getParameter("nowPage");
		String cntPerPage=request.getParameter("cntPerPage");
		BoardService bs=sqlsession.getMapper(BoardService.class);
		int total=bs.total();
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
		mo.addAttribute("list", bs.page(dto));
		return "gongjiboard";
	}
	
	@RequestMapping(value = "/gongjidetail", method = RequestMethod.GET)
	public String gongjidetail(Model mo, HttpServletRequest request) {
		int gnum=Integer.parseInt(request.getParameter("gnum"));
		BoardService bs=sqlsession.getMapper(BoardService.class);
		bs.gongjicount(gnum);
		GongjiDTO list=bs.gongjidetail(gnum);
		mo.addAttribute("list",list);
		return "gongjiout";
	}
	
	@RequestMapping(value = "/gongjidelete", method = RequestMethod.GET)
	public String gongjidelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int gnum=Integer.parseInt(request.getParameter("gnum"));
		BoardService bs=sqlsession.getMapper(BoardService.class);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter prw=response.getWriter();
		prw.print("<script> alert('해당 글을 삭제하였습니다.');</script>");
		prw.print("<script> location.href='gongjiboard';</script>");
		prw.close();
		bs.gongjidelete(gnum);
		return "redirect:/gongjiboard";
	}
	
	@RequestMapping(value = "/gongjiupdateview", method = RequestMethod.GET)
	public String gongjiupdateview(HttpServletRequest request, Model mo) {
		int gnum=Integer.parseInt(request.getParameter("gnum"));
		BoardService bs=sqlsession.getMapper(BoardService.class);
		GongjiDTO list=bs.gongjiupdateview(gnum);
		mo.addAttribute("list", list);
		return "gongjiupdateview";
	}
	
	@RequestMapping(value = "/gongjiupdate", method = RequestMethod.POST)
	public String gongjiupdate(HttpServletResponse response, MultipartHttpServletRequest mul) throws IOException {
		int gnum=Integer.parseInt(mul.getParameter("gnum"));
		String id=mul.getParameter("id");
		String nickname=mul.getParameter("nickname");
		String gtitle=mul.getParameter("gtitle");
		String gcontents=mul.getParameter("gcontents");
		MultipartFile mf=mul.getFile("gimage");
		String gimagefn=mf.getOriginalFilename();
		mf.transferTo(new File(savepath+"//"+gimagefn));
		BoardService bs=sqlsession.getMapper(BoardService.class);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter prw=response.getWriter();
		prw.print("<script> alert('수정이 완료되었습니다.');</script>");
		prw.print("<script> location.href='gongjiboard';</script>");
		prw.close();
		bs.gongjiupdate(gnum, id, nickname, gtitle, gcontents, gimagefn);
		return "redirect:/gongjidetail?gnum=" + gnum;
	}
	
	@RequestMapping(value = "/gongjisearchsave", method = RequestMethod.POST)
	public String gongjisearchsave(HttpServletRequest request, Model mo, PageDTO dto, @RequestParam("svalue") String svalue, @RequestParam("gongjikey") String gongjikey, HttpServletResponse response) throws IOException {
		String nowPage=request.getParameter("nowPage");
		String cntPerPage=request.getParameter("cntPerPage");
		BoardService bs=sqlsession.getMapper(BoardService.class);
		
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
	    if (svalue == null || svalue.trim().equals("")) {
	        response.setContentType("text/html;charset=utf-8");
	        PrintWriter prw = response.getWriter();
			prw.print("<script> alert('검색어를 입력해주세요.');</script>");
			prw.print("<script> location.href='gongjiboard';</script>");
	        prw.flush();
	        prw.close();
	        return null;
		}
		else {
			svalue="%"+svalue+"%";
			if(gongjikey.equals("gtitle")) {
				int totalt=bs.totalt(svalue);
				dto=new PageDTO(totalt,Integer.parseInt(nowPage),Integer.parseInt(cntPerPage));
				mo.addAttribute("paging",dto);
				mo.addAttribute("list", bs.paget(dto, svalue));
			}
			else if(gongjikey.equals("gcontents")) {
				int totalc=bs.totalc(svalue);
				dto=new PageDTO(totalc,Integer.parseInt(nowPage),Integer.parseInt(cntPerPage));
				mo.addAttribute("paging",dto);
				mo.addAttribute("list", bs.pagec(dto, svalue));
			}
			else {
				int totaln=bs.totaln(svalue);
				dto=new PageDTO(totaln,Integer.parseInt(nowPage),Integer.parseInt(cntPerPage));
				mo.addAttribute("paging",dto);
				mo.addAttribute("list", bs.pagen(dto, svalue));
			}
			return "gongjisearchview";
		}
	}
	
	@RequestMapping(value = "/boardinput", method = RequestMethod.GET)
	public String boardinput() {
		return "boardinput";
	}
	
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String board(Model mo, PageDTO dto, HttpServletRequest request) {
		String nowPage=request.getParameter("nowPage");
		String cntPerPage=request.getParameter("cntPerPage");
		BoardService bs=sqlsession.getMapper(BoardService.class);
		int btotal=bs.boardtotal();
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
		dto=new PageDTO(btotal,Integer.parseInt(nowPage),Integer.parseInt(cntPerPage));
		mo.addAttribute("paging",dto);
		mo.addAttribute("list", bs.boardpage(dto));
		return "board";
	}
	
	@RequestMapping(value = "/boardsave", method = RequestMethod.POST)
	public String boardsave(MultipartHttpServletRequest mul) throws IllegalStateException, IOException {
		String tag=mul.getParameter("tag");
		String nickname=mul.getParameter("nickname");
		String title=mul.getParameter("title");
		String ccontents=mul.getParameter("ccontents");
		MultipartFile mf=mul.getFile("cimage");
		String cimagefn=mf.getOriginalFilename();
		mf.transferTo(new File(savepath+"//"+cimagefn));
		BoardService bs=sqlsession.getMapper(BoardService.class);
		bs.insertboard(nickname, tag, title, ccontents, cimagefn);
		return "redirect:/board";
	}
	
	@RequestMapping(value = "/boarddetail", method = RequestMethod.GET)
	public String boarddetail(Model mo, HttpServletRequest request) {
		int cnum=Integer.parseInt(request.getParameter("cnum"));
		BoardService bs=sqlsession.getMapper(BoardService.class);
		bs.boardcount(cnum);
		BoardDTO list=bs.boarddetail(cnum);
		mo.addAttribute("list",list);
		ArrayList<BoardDTO>reply=bs.replyout(cnum);
		mo.addAttribute("reply",reply);
		return "boardout";
	}
	
	@RequestMapping(value = "/boardlike", method = RequestMethod.GET)
	public String boardlike(Model mo, @RequestParam("cnum") int cnum) {
		BoardService bs=sqlsession.getMapper(BoardService.class);
		bs.boardlike(cnum);
		BoardDTO list=bs.boarddetail(cnum);
		mo.addAttribute("list",list);
		return "redirect:/boarddetail?cnum=" + cnum;
		}
	
	@RequestMapping(value = "/boardhate", method = RequestMethod.GET)
	public String boardhate(Model mo, @RequestParam("cnum") int cnum) {
		BoardService bs=sqlsession.getMapper(BoardService.class);
		bs.boardhate(cnum);
		BoardDTO list=bs.boarddetail(cnum);
		mo.addAttribute("list",list);
		return "redirect:/boarddetail?cnum=" + cnum;
	}
	
	@RequestMapping(value = "/boardsearchsave", method = RequestMethod.POST)
	public String boardsearchsave(HttpServletRequest request, Model mo, PageDTO dto, @RequestParam("svalue") String svalue, @RequestParam("boardkey") String boardkey, HttpServletResponse response) throws IOException {
		String nowPage=request.getParameter("nowPage");
		String cntPerPage=request.getParameter("cntPerPage");
		BoardService bs=sqlsession.getMapper(BoardService.class);
		
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
	    if (svalue == null || svalue.trim().equals("")) {
	        response.setContentType("text/html;charset=utf-8");
	        PrintWriter prw = response.getWriter();
			prw.print("<script> alert('검색어를 입력해주세요.');</script>");
			prw.print("<script> location.href='board';</script>");
	        prw.flush();
	        prw.close();
	        return null;
		}
		else {
			svalue="%"+svalue+"%";
			if(boardkey.equals("ctitle")) {
				int totalct=bs.totalct(svalue);
				dto=new PageDTO(totalct,Integer.parseInt(nowPage),Integer.parseInt(cntPerPage));
				mo.addAttribute("paging",dto);
				mo.addAttribute("list", bs.pagect(dto, svalue));
			}
			else if(boardkey.equals("ccontents")) {
				int totalcc=bs.totalcc(svalue);
				dto=new PageDTO(totalcc,Integer.parseInt(nowPage),Integer.parseInt(cntPerPage));
				mo.addAttribute("paging",dto);
				mo.addAttribute("list", bs.pagecc(dto, svalue));
			}
			else {
				int totalcn=bs.totalcn(svalue);
				dto=new PageDTO(totalcn,Integer.parseInt(nowPage),Integer.parseInt(cntPerPage));
				mo.addAttribute("paging",dto);
				mo.addAttribute("list", bs.pagecn(dto, svalue));
			}
			return "boardsearchview";
		}
	}//
	
	@RequestMapping(value = "/boardreplysave", method = RequestMethod.POST)
	public String boardreplysave(HttpServletRequest request, Model mo) {
		int rpcnum=Integer.parseInt(request.getParameter("cnum"));
		int rpcgroup=Integer.parseInt(request.getParameter("cgroup"));
		int rpstep=Integer.parseInt(request.getParameter("step"));
		int rpindent=Integer.parseInt(request.getParameter("indent"));
		String ccontents=request.getParameter("ccontents");
		BoardService bs=sqlsession.getMapper(BoardService.class);
		bs.stepup(rpcgroup, rpstep);
		rpstep++;
		rpindent++;
		bs.replyinsertb(rpcnum, rpcgroup, rpstep, rpindent, ccontents);
	    return "redirect:/boarddetail?cnum=" + rpcnum;
	}//
	
	@RequestMapping(value = "/boarddelete", method = RequestMethod.GET)
	public String boarddelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int cnum=Integer.parseInt(request.getParameter("cnum"));
		BoardService bs=sqlsession.getMapper(BoardService.class);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter prw=response.getWriter();
		prw.print("<script> alert('해당 글을 삭제하였습니다.');</script>");
		prw.print("<script> location.href='board';</script>");
		prw.close();
		bs.boarddelete(cnum);
		return "redirect:/board";
	}
	
	@RequestMapping(value = "/boardupdateview", method = RequestMethod.GET)
	public String boardupdateview(HttpServletRequest request, Model mo) {
		int cnum=Integer.parseInt(request.getParameter("cnum"));
		BoardService bs=sqlsession.getMapper(BoardService.class);
		BoardDTO list=bs.boardupdateview(cnum);
		mo.addAttribute("list", list);
		return "boardupdateview";
	}
	
	@RequestMapping(value = "/boardupdate", method = RequestMethod.POST)
	public String boardupdate(HttpServletResponse response, MultipartHttpServletRequest mul) throws IOException {
		int cnum=Integer.parseInt(mul.getParameter("cnum"));
		String tag=mul.getParameter("tag");
		String nickname=mul.getParameter("nickname");
		String title=mul.getParameter("title");
		String ccontents=mul.getParameter("ccontents");
		MultipartFile mf=mul.getFile("cimage");
		String cimagefn=mf.getOriginalFilename();
		mf.transferTo(new File(savepath+"//"+cimagefn));
		BoardService bs=sqlsession.getMapper(BoardService.class);
		bs.boardupdate(cnum, nickname, tag, title, ccontents, cimagefn);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter prw=response.getWriter();
		prw.print("<script> alert('수정이 완료되었습니다.');</script>");
		prw.print("<script> location.href='board';</script>");
		prw.close();
		return "redirect:/boarddetail?cnum=" + cnum;
	}

	@RequestMapping(value = "/iljung", method = RequestMethod.GET)
	public String iljung() {
		return "iljung";
	}
	
	@RequestMapping(value = "/iljunginput", method = RequestMethod.GET)
	public String iljunginput() {
		return "iljunginput";
	}
	
	@RequestMapping(value = "/iljungsave", method = RequestMethod.POST)
	public String iljungsave(HttpServletRequest request) {
		String gamedate=request.getParameter("gamedate");
		String home=request.getParameter("home");
		String away=request.getParameter("away");
		int homescore=Integer.parseInt(request.getParameter("homescore"));
		int awayscore=Integer.parseInt(request.getParameter("awayscore"));
		String gamestate=request.getParameter("gamestate");
		String gameresult="";
		if(gamestate.equals("진행")) {
			gameresult=home+" "+homescore+" : "+awayscore+" "+away;
		}
		else {
			gameresult=home+" 취소 "+away;
		}
		BoardService bs=sqlsession.getMapper(BoardService.class);
		bs.iljunginput(gamedate, gameresult);
		return "iljung";
	}
	
	@RequestMapping(value = "/events", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Map<String, Object>> events() {
	    BoardService bs = sqlsession.getMapper(BoardService.class);
	    List<IljungDTO> list = bs.iljungout();

	    if (list == null || list.isEmpty()) {
	        System.out.println("데이터가 없습니다.");
	    } else {
	        System.out.println("데이터 있음: " + list.size() + "개의 일정");
	    }

	    List<Map<String, Object>> jsonList = new ArrayList<>();
	    for (IljungDTO dto : list) {
	        Map<String, Object> event = new HashMap<>();
	        event.put("title", dto.getGameresult());
	        event.put("start", dto.getGamedate()); // 날짜 형식 맞춰주기
	        jsonList.add(event);
	    }
	    System.out.println("JSON 데이터: " + jsonList);
	    return jsonList; // JSON 형식으로 반환
	}


}
