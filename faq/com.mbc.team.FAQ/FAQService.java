package com.mbc.team.FAQ;

import java.util.ArrayList;

public interface FAQService {
//1. ����(���Ǳ� �ۼ�)
	void faqinsert(String tab, String title, String fcontents, 
			String nickname, String fname1, String fname2, String fname3);
//2. �Խ���
	ArrayList<FAQDTO> faqboard();
//3. ���Ǳ� ��
	ArrayList<FAQDTO> faqdetail(int cnum);
//4. ���Ǳ� ����â
	FAQDTO faqupdate(int cnum);
//5. ���Ǳ� �����Ϸ�
	void faqupdate2(int cnum, String tab, String title, String fcontents, 
			String nickname, String fname1, String fname2, String fname3);
//6. ���Ǳ� ����
	void faqdelete(int cnum);
//7. ������	
	public int total();
    public ArrayList<FAQDTO> page(PageDTO dto);
//8. ���� �亯
	ArrayList<FAQDTO> faqreply(int cnum);
//9-1. ���Ǿ�
	void faqstepup(int groups, int step);
//9-2. ���� �亯 ���� ����	
	void faqreplysave(int cnum, String tab, String title, String fcontents, 
			String nickname, int groups, int step,	int indent);
//10. Ű�� �˻�
	//title(����)
	ArrayList<FAQDTO> faqtitlesearch(String faqvalue);
	//nickname(�ۼ���)
	ArrayList<FAQDTO> faqnicknamesearch(String faqvalue);
	

}
