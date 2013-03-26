package com.taogongbao.common.constant;

/**
 * <pre>
 * 字典表对应常量
 * @createTime: 2010-11-20 上午05:57:41
 * @author: <a href="mailto:liyouliang@feinno.com">李友良</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime: 
 * @updateAuthor: <a href="mailto:liyouliang@feinno.com">李友良</a>
 * @changesSum:
 * </pre>
 */
public class DictCodes {
	
	/**显示默认值、不详*/
	public final static String DEFAULT_POP_VALUE = "-1";  //下拉框默认初始值
	/**不限*/
	public final static String UNLIMIT_POP_VALUE = "0";  //下拉框默认初始值
	
	/**面试结果 MSJG*/
	public final static String MSJG = "MSJG";
	public class Msjg {
		public final static String WAIT_INTERVIEW = "1"; // 默认值: 等待面试  
		public final static String ON_INTERVIEW = "2"; // 正在面试
		public final static String INTERVIEW_FAILURE = "3"; // 面试未通过
		public final static String PASS_REVIEW = "4"; // 面试通过
	}
	/**建立状态 JSZT*/
	public final static String JSZT = "JSZT";
	public class Jszt {
		public final static String NORMAL_STATE = "1"; // 默认值: 正常状态  
		public final static String STOP_STATE = "2"; // 停用状态
	}
	
	/**个人维护状态 JLZT*/
	public final static String JLZT = "JLZT";
	public class Jlzt {
		public final static String STORE_STATE = "1"; // 暂存状态  
		public final static String NORMAL_STATE = "2"; // 正常状态
		public final static String CLOSE_STATE = "3"; // 关闭状态
	}
	
	/**简历星级JLXJ*/
	public final static String JLXJ = "JLXJ";
	public class Jlxj {
		public final static String STAR_LEVEL_1 = "1"; // 1级  
		public final static String STAR_LEVEL_2 = "2"; // 2级   
		public final static String STAR_LEVEL_3 = "3"; // 默认值: 3级  
		public final static String STAR_LEVEL_4 = "4"; // 4级  
		public final static String STAR_LEVEL_5 = "5"; // 5级  
	}
	
	/**岗位计薪方式JXFS*/
	public final static String JXFS = "JXFS";
	public class Jxfs {
		public final static String BY_MONTH = "1"; // 按月  
		public final static String BY_COUNTS = "2"; // 计件   
		public final static String BY_DAY = "3"; // 按天
		public final static String BY_NEGOTIABLE = "4"; // 面议
	}
	/**保险项详细信息BXXXX*/
	public final static String BXXXX = "BXXXX";
	public class Bxxxx {
		public final static String SHEBAO = "1"; // 社保  
		public final static String YIBAO = "2"; // 医保   
		public final static String SHENGYU = "3"; // 生育
		public final static String SHIYE = "4"; // 失业	
		public final static String GONGSHANG = "5"; // 失业	
		
	}
	/**招工团审核状态ZGTSHZT*/
	public final static String ZGTSHZT = "ZGTSHZT";
	public class Zgtshzt {
		public final static String WEISHENG = "1"; // 社保  
	}
	/**招工状态ZGTSHZT*/
	public final static String ZGZT = "ZGZT";
	public class Zgzt {
		public final static String NORMAL_STATE = "1"; // 正常  
	}
	/**招工进度状态ZGJDZT*/
	public final static String ZGJDZT = "ZGJDZT";
	public class Zgjdzt {
		public final static String FULL_STATE = "1"; // 正常  
		//to do
	}
	/**报名状态ZGJDZT*/
	public final static String BMZT = "BMZT";
	public class Bmzt {
		public final static String APPLY_STATE = "1"; // 正常  
	}
	/**岗位审核状态GWSHZT*/
	public final static String GWSHZT = "GWSHZT";
	public class Gwshzt {
		public final static String NOT_CHECK_STATE = "1"; // 未审核 
		public final static String CHECK_FAIL = "2"; // 驳回
		public final static String CHECK_SUCCESS = "3"; // 审核通过 
	}
	/**企业用户发布ZWFBLX*/
	public final static String ZWFBLX = "ZWFBLX";
	public class Zwfblx {
		public final static String QIYEFABU = "1"; // 企业用户发布
	}
	/**信息状态XXZT*/
	public final static String XXZT = "XXZT";
	public class Xxzt {
		public final static String NOTREAD = "1"; // 未读信息
	}
	/**面试状态MSZT*/
	public final static String MSZT = "MSZT";
	public class Mszt{
		public final static String NORMAL_MSZT_STATE = "1"; // 正常状态
	}
	/**企业操作简历状态QYCZJLZT*/
	public final static String QYCZJLZT = "QYCZJLZT";
	public class Qyczjlzt{
		public final static String POST_QYCZJLZT_STATE = "1"; // 投递状态
	}


	
	// 用户类型
	public final static String YHLX = "YHLX";
	
	public class Yhlx {
		public final static String PERSONAL_TYPE = "1"; // 个人
		public final static String ENTERPRISE_TYPE = "2";// 企业
		public final static String USERTYPE_CHANNEL_TYPE = "4";// 渠道
	}
	
	public final static String YHZT ="YHZT";
	/** 用户状态 */
	public class Yhzt {
		public final static String ENABLE = "1"; // 默认值：正常 
		public final static String DISABLE = "2";// 删除
		public final static String TRIAL = "3";// 试用
	}

	/** 企业审核状态 */
	public class Qyshzt {
		public final static String UNCHECK = "1"; // 默认值：未审  
		public final static String CEHECKED = "2"; // 审核通过
		public final static String REFUSED = "3"; // 拒绝
	}

	/** 职位发布状态 */
	public class Fbzt {
		public final static String TEMP = "1"; // 草稿
		public final static String STORE = "2"; // 暂存
		public final static String PUB = "3"; // 发布
		public final static String STOP = "4"; // 暂停
		public final static String OVER = "5"; // 结束
		public final static String DEL = "99"; // 逻辑删除
	}

	/** 职位发布者类型 */
	public class Fbzlx {
		public final static String ENT = "1"; // 企业
		public final static String CHANNEL = "2"; // 渠道
	}

	/** 职位审核状态 */
	public class Zwshzt {
		public final static String NOTCHECK = "1"; // 1 未审核
		public final static String FAIL = "2"; // 2 驳回
		public final static String PASS = "3"; // 3 审核通过
	}
	
	/** 职位审核状态 */
	public class Zzfwshzt {
		public final static String NOTCHECK = "1"; // 1 未审核
		public final static String FAIL = "2"; // 2 驳回
		public final static String PASS = "3"; // 3 审核通过
	}
	
	/** 渠道维护状态 */
	public class Qdwhqyzt {
		public final static String ENABLE = "1"; // 默认值：正常 
		public final static String STOP = "2"; // 暂停
	}
	
	/** 日志分类*/
	public class Dlrzfl {
		public final static String OPERATION = "1"; // 操作日志 
		public final static String UPGRADE = "2"; // 升级日志
		public final static String LOGIN = "3"; // 登录日志
	}

	/**
     * 薪资状态表默认值
     * @author lihanpei
     */
    public class SalaryMannerStates {
        public static final String DEFAULT_SALARY_MANNER = "面议";
    }
    
    /**
     * 广告发布状态
     * @author zhouyoucheng
     */
    public class AdStates {
        public static final String TO_PROCESSING = "2";
        public static final String PROCESSING = "1";
    }
    
    /**
     * 招聘会标记状态
     * @author ZhouYouCheng
     */
    public class isNewStates {
    	public static final String IS_NEW = "1" ; //已标记
    	public static final String IS_OLD = "0" ; //未标记
    }
    
    public class EnterpressExt {
        public final static int Remain_Job_Count = -1;//企业发布剩余职位数量
        public final static int Remain_Download_Count = 10;//企业剩余查看简历载量
        public final static int Can_Download_Cont = 10;//企业每天可用下载数量
    }
    
    /**
     * 用户类型
     */
    public class UserType {
    	public static final int INTERNAL_USER = 1;//内部用户
    	public static final int EXTERNAL_USER = 2;//外部用户
    }
}
