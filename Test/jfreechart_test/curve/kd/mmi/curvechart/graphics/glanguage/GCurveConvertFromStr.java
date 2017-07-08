package curve.kd.mmi.curvechart.graphics.glanguage;



public class GCurveConvertFromStr {

	public static String G_FILE_POSTFIX = "curve.g";

	public static String CLASS_INTEGER = "java.lang.Integer";
	public static String CLASS_FLOAT = "java.lang.Float";
	public static String CLASS_BOOLEAN = "java.lang.Boolean";

	public static String SGD = "sgd";

	public static String CURVE = "Curve";
	public static String GRID = "Grid";
	public static String X_AXIS = "X-axis";
	public static String Y_AXIS = "Y-axis";
	public static String ENTITY = "Entity";
	public static String DYNAMIC = "Dynamic";
	public static String LEGEND = "Legend";
	public static String TEXT_FIELD = "TextField";
	public static String DATA_FIELD = "DataField";
	public static String FOCUS_FIELD = "FocusField";
	public static String TURNOVER_FIELD = "TurnOverField";

	public static final int COLOR_WITHE = -1; // 白色
	public static final int COLOR_BLACK = 0; // 黑色

	public static final int BOOLEAN_TRUE = 1;
	public static final int BOOLEAN_FALSE = 0;
	
	public static final int MAIN_SPACE_DEFAULT = 4;
	public static final int SUB_SPACE_DEFAULT = 1;
	public static final int RUN_UNIT = 0;
	public static String DEFAULT_FONT_FAMILY = "SimSun";
	public static final int DEFAULT_FONT_WEIGHT = 0;
	public static final int DEFAULT_FONT_SIZE = 12;
	public static final int DEFAULT_FONT_COLOR = 0;
	public static final int DEFAULT_TIME_START = 0;
	public static final int DEFAULT_TIME_END = 24;
	public static final int DEFAULT_TIME_SPAN = 24;
	public static final int DEFAULT_TIME_INT_VALUE = 0;// 默认取整时间值
	public static final float DEFAULT_MAX_VALUE = 100f;// 默认最大值
	public static final float DEFAULT_MIN_VALUE = 0f;// 默认最小值
	public static final int DEFAULT_DECIMAL_VALUE = 2;// 默认小数精度

	/** 通用属性 Curve */
	public static String CHART_X = "x"; // 模板左上角X坐标值
	public static String CHART_Y = "y"; // 模板左上角Y坐标值
	public static String CHART_HEIGHT = "h"; // 模板高度
	public static String CHART_WIDTH = "w"; // 模板宽度
	public static String CHART_BACKGROUND_COLOR = "bgc";// 模板背景颜色

	public static String CHART_BACKGROUND_FILLPATTERN = "bgm";// 背景填充模式
	public static final int CHART_BACKGROUND_FILL_SINGLE = 0;// 单色
	public static final int CHART_BACKGROUND_FILL_TRANSPARENT = 1;// 透明
	public static final int CHART_BACKGROUND_FILL_PIC = 2;// 背景图片(贴片)

	public static String CHART_BACKGROUND_PIC = "pic_url"; // 背景图片路径名称
	public static String GRID_BACKGROUND_COLOR = "cgc";// 网格背景颜色(布景色)
	public static String TITLE_SHOW = "title_show";// 显示标题
	public static String TITLE_CONTENT = "title_content";// 标题内容

	public static String REFRESH_INTERVAL = "interval";// 刷新间隔,单位秒
	public static final int REFRESH_DEFAULT_PERIOD = 60;// 默认刷新周期60秒

	public static String MULTI_AXIS_TYPE = "multi_axis_type";// 多轴类型
	public static final int MULTI_AXIS_TYPE_SINGLE = 0;// 单轴
	public static final int MULTI_AXIS_TYPE_DOUBLE = 1;// 双轴
	public static final int MULTI_AXIS_TYPE_MULTI = 2;// 多轴

	/** 网格背景 Grid */
	public static final int LINE_DEFAULT_WIDTH = 1; // 默认线宽
	public static final int LINE_STYLE_NULL = 0; // 无
	public static final int LINE_STYLE_SOLID = 1; // 实线
	public static final int LINE_STYLE_DASH = 2; // 虚线 4-2
	public static final int LINE_STYLE_DOT = 3; // 虚线 1-2
	public static final int LINE_STYLE_DASH_DOT = 4; // 虚线 4-2-1-2
	public static final int LINE_STYLE_DASH_DOT_DOT = 5; // 虚线 4-2-1-2-1-2

	public static String MAIN_GRID_X = "main_grid_x";// 主网格左上角X坐标
	public static String MAIN_GRID_Y = "main_grid_y";// 主网格左上角Y坐标
	public static String MAIN_GRID_WIDTH = "main_grid_w";// 主网格宽度
	public static String MAIN_GRID_HEIGHT = "main_grid_h";// 主网格高度
	public static String MAIN_GRID_COLOR = "main_grid_c";// 主网格颜色
	public static String MAIN_GRID_LINE_WIDTH = "main_grid_lw";// 主网格线宽
	public static String MAIN_GRID_LINE_STYLE = "main_grid_ls";// 主网格线型
	public static String SUB_GRID_COLOR = "sub_grid_c";// 副网格颜色
	public static String SUB_GRID_LINE_WIDTH = "sub_grid_lw";// 副网格线宽
	public static String SUB_GRID_LINE_STYLE = "sub_grid_ls";// 副网格线型
	public static String MAIN_GRID_X_SHOW = "main_grid_x_show";// 显示X方向主网格
	public static String SUB_GRID_X_SHOW = "sub_grid_x_show";// 显示X方向副网格
	public static String MAIN_GRID_Y_SHOW = "main_grid_y_show";// 显示Y方向主网格
	public static String SUB_GRID_Y_SHOW = "sub_grid_y_show";// 显示Y方向副网格

	/** 横坐标属性 X-axis */
	public static String AXIS_X_SCALE_TYPE = "scale";// 横坐标显示类型
	public static final int AXIS_X_SCALE_TYPE_NUM = 0; // 数字坐标
	public static final int AXIS_X_SCALE_TYPE_TIME = 1; // 时间坐标
	public static final int AXIS_X_SCALE_TYPE_RTDB = 2; // 实时库
	public static final int AXIS_X_SCALE_TYPE_RDB = 3; // 关系库

	public static String AXIS_X_SHOW = "show";// 横坐标标尺是否显示
	public static String AXIS_X_RUN = "run";// 横坐标是否跑动
	public static String AXIS_X_RUN_PACE = "runpace";// 跑动步长
	public static String AXIS_X_PACE = "pace";// 横坐标步长
	public static String AXIS_X_PACE_UNIT = "unit";// 横坐标步长单位
	public static String AXIS_X_PREPARATORY_PERCENT = "percent";// 横坐标预留百分比
	public static String AXIS_X_MAIN_SPACE = "mainspace";// 横坐标主间距
	public static String AXIS_X_SUB_SPACE = "subspace";// 横坐标次间距
	public static String AXIS_X_ISPOINT = "ispoint";// 是否按照点数绘制
	public static String AXIS_X_POINT = "point";// 采样点数

	public static String AXIS_X_POINT_MODE = "pointmode";// 数据源方式
	public static final int AXIS_X_POINT_MODE_EQUIDISTANCE = 0;// 点等距
	public static final int AXIS_X_POINT_MODE_RAW = 1;// 自然数据
	public static final int AXIS_X_POINT_MODE_EQUIDISTANCE_TIME = 2; // 点等距(时间Tip)

	public static String AXIS_X_POINT_DIRECTION = "pointdirection";// 采样点数方向
	public static final int AXIS_X_POINT_DIRECTION_HORIZONTAL = 0;// 横向
	public static final int AXIS_X_POINT_DIRECTION_VERTICAL = 1;// 纵向

	public static String AXIS_X_POINT_SPACE = "pointspace";// 采样点域间距
	public static String AXIS_X_FONT_FAMILY = "ff";// 横坐标轴刻度字体名称
	public static String AXIS_X_FONT_WEIGHT = "fw";// 横坐标轴刻度字体类型
	public static String AXIS_X_FONT_SIZE = "fs";// 横坐标轴刻度字体大小
	public static String AXIS_X_FONT_COLOR = "fc";// 横坐标轴刻度字体颜色
	/** 时间坐标 */
	public static String AXIS_X_TIME_FORMAT = "format";// 标尺格式
	public static final int AXIS_X_TIME_FORMAT_SS = 0; // 秒
	public static final int AXIS_X_TIME_FORMAT_MM = 1; // 分钟
	public static final int AXIS_X_TIME_FORMAT_HH = 2; // 小时
	public static final int AXIS_X_TIME_FORMAT_MM_SS = 3; // 分钟:秒（10：00）
	public static final int AXIS_X_TIME_FORMAT_HH_MM = 4; // 小时:分钟（09：30）
	public static final int AXIS_X_TIME_FORMAT_YYYY_MM_DD = 5; // 年-月-日（2010-03-02）
	public static final int AXIS_X_TIME_FORMAT_YYYY_MM = 6; // 年-月（2010-03）
	public static final int AXIS_X_TIME_FORMAT_YYYY = 7; // 年（2010）
	public static final int AXIS_X_TIME_FORMAT_MM_DD = 8; // 月-日（03-02）
	public static final int AXIS_X_TIME_FORMAT_DD = 9; // 日（02）
	public static String AXIS_X_TIME_FORMAT_SS_STR = "秒";
	public static String AXIS_X_TIME_FORMAT_MM_STR = "分";
	public static String AXIS_X_TIME_FORMAT_HH_STR = "时";
	public static String AXIS_X_TIME_FORMAT_MM_SS_STR = "分:秒";
	public static String AXIS_X_TIME_FORMAT_HH_MM_STR = "时:分";
	public static String AXIS_X_TIME_FORMAT_YYYY_MM_DD_STR = "年-月-日";
	public static String AXIS_X_TIME_FORMAT_YYYY_MM_STR = "年-月";
	public static String AXIS_X_TIME_FORMAT_YYYY_STR = "年";
	public static String AXIS_X_TIME_FORMAT_MM_DD_STR = "月-日";
	public static String AXIS_X_TIME_FORMAT_DD_STR = "日";  //  @jve:decl-index=0:
	public static String AXIS_X_RUN_UNIT = "rununit";
	
	public static String AXIS_X_TIME_START = "start";// 开始时刻
	public static String AXIS_X_TIME_END = "end";// 结束时刻
	public static String AXIS_X_TIME_IS_ABS = "abs_time"; // 是否绝对时间
	public static String AXIS_X_TIME_IS_INT = "int_time"; // 刻度时间是否取整
	public static String AXIS_X_TIME_INT_TIME_VALUE = "int_time_value"; // 刻度时间是否取整
	public static String AXIS_X_TIME_SPAN = "span";// 坐标轴时间跨度

	public static String AXIS_X_TIME_UNIT = "unit";// 时间间隔单位
	public static final int AXIS_X_TIME_UNIT_SECOND = 0;
	public static final int AXIS_X_TIME_UNIT_MINUTE = 1;
	public static final int AXIS_X_TIME_UNIT_HOUR = 2;
	public static final int AXIS_X_TIME_UNIT_DAY = 3;
	public static final int AXIS_X_TIME_UNIT_MONTH = 4;
	public static final int AXIS_X_TIME_UNIT_YEAR = 5;
	public static final int AXIS_X_TIME_UNIT_WEEK = 6;
	public static final int AXIS_X_TIME_UNIT_QUARTER = 7;
	public static String AXIS_X_TIME_UNIT_SECOND_STR = "秒";
	public static String AXIS_X_TIME_UNIT_MINUTE_STR = "分";
	public static String AXIS_X_TIME_UNIT_HOUR_STR = "时";
	public static String AXIS_X_TIME_UNIT_DAY_STR = "日";
	public static String AXIS_X_TIME_UNIT_MONTH_STR = "月";
	public static String AXIS_X_TIME_UNIT_YEAR_STR = "年";
	public static String AXIS_X_TIME_UNIT_WEEK_STR = "周";
	public static String AXIS_X_TIME_UNIT_QUARTER_STR = "季度";

	/** 数字坐标 */
	public static String AXIS_X_NUMBER_MAX = "max";// 横坐标最大值
	public static String AXIS_X_NUMBER_MIN = "min";// 横坐标最小值
	public static String AXIS_X_NUMBER_DECIMAL = "decimal";// 横坐标小数位数

	/** 纵坐标属性 Y-axis */
	public static String AXIS_Y_AUTO_SCALE = "auto";// 左坐标是否自适应
	public static String AXIS_Y_SHOW = "show";// 纵标尺是否显示

	public static String AXIS_Y_CNUM = "cnum"; //曲线属性
	
	public static String MULTI_AXIS_DEF = "multi_axis_def";// 纵坐标多坐标定义,0默认为单轴坐标,奇数为左轴坐标,偶数为右轴坐标
	public static final int MULTI_AXIS_SINGLE = 0; // 单轴
	public static final int MULTI_AXIS_LEFT_1 = 1; // 左1
	public static final int MULTI_AXIS_RIGHT_1 = 2; // 右1
	public static final int MULTI_AXIS_LEFT_2 = 3; // 左2
	public static final int MULTI_AXIS_RIGHT_2 = 4; // 右2

	public static String AXIS_Y_MAIN_SPACE = "mainspace";// X轴坐标主间距
	public static String AXIS_Y_SUB_SPACE = "subspace";// X轴坐标次间距
	public static String AXIS_Y_NUMBER_MAX = "max";// 纵坐标最大值
	public static String AXIS_Y_NUMBER_MIN = "min";// 纵坐标最小值
	public static String AXIS_Y_NUMBER_DECIMAL = "decimal";// 纵坐标小数位数
	public static String AXIS_Y_MAX_RATE = "maxrate";// 纵坐标最大值上浮比例
	public static String AXIS_Y_MIN_RATE = "minrate";// 纵坐标最小值上浮比例
	public static String AXIS_Y_IS_INTEGER = "isinteger";// 纵坐标坐标刻度是否规整
	public static String AXIS_Y_AUTO_WITH_LIMIT = "autowithlimit";// 纵坐标坐标刻度是否限值内自适应
	public static String AXIS_Y_FONT_FAMILY = "ff";// 横坐标轴刻度字体名称
	public static String AXIS_Y_FONT_WEIGHT = "fw";// 横坐标轴刻度字体类型
	public static String AXIS_Y_FONT_SIZE = "fs";// 横坐标轴刻度字体大小
	public static String AXIS_Y_FONT_COLOR = "fc";// 横坐标轴刻度字体颜色
	public static final String AXIS_Y_PACE = "pace";//标尺步长

	/** 图例属性 Legend */
	public static String LEGEND_X = "x";// 图例左上角X坐标
	public static String LEGEND_Y = "y";// 图例左上角Y坐标
	public static String LEGEND_FONT_FAMILY = "ff";// 图例字体名称
	public static String LEGEND_FONT_WEIGHT = "fw";// 图例字体类型
	public static String LEGEND_FONT_SIZE = "fs";// 图例字体大小
	public static String LEGEND_FONT_COLOR = "fc";// 图例字体大小

	/** 翻页域属性 TurnOverField */
	public static String TURNOVER_FIELD_X = "x";// 翻页域左上角X坐标
	public static String TURNOVER_FIELD_Y = "y";// 翻页域左上角Y坐标
	public static String TURNOVER_FIELD_HEIGHT = "h";// 翻页域高度
	public static String TURNOVER_FIELD_WIDTH = "w";// 翻页域宽度
	public static String TURNOVER_FIELD_COLOR = "color";// 翻页域颜色

	public static String TURNOVER_FIELD_TYPE = "type";// 翻页域类型
	public static final int TURNOVER_FIELD_TYPE_PRE = 0;// 前翻
	public static final int TURNOVER_FIELD_TYPE_NEXT = 1;// 后翻

	/** 文字域属性 TextField */
	public static String TEXT_FIELD_X = "x";// 文字域左上角X坐标
	public static String TEXT_FIELD_Y = "y";// 文字域左上角Y坐标
	public static String TEXT_FIELD_HEIGHT = "h";// 文字域高度
	public static String TEXT_FIELD_WIDTH = "w";// 文字域宽度
	public static String TEXT_FIELD_FONT_FAMILY = "ff";// 文字域字体名称
	public static String TEXT_FIELD_FONT_WEIGHT = "fw";// 文字域字体类型
	public static String TEXT_FIELD_FONT_SIZE = "fs";// 文字域字体大小
	public static String TEXT_FIELD_FONT_COLOR = "fc";// 文字域字体颜色

	public static String TEXT_FIELD_TYPE = "type";// 文字域类型
	public static final int TEXT_FIELD_TYPE_TITLE = 0;// 标题
	public static final int TEXT_FIELD_TYPE_OTHER = 255;// 其它内容

	public static String TEXT_FIELD_CONTENT = "content"; // 文字域内容

	/** 数据域属性 DataField */
	public static String DATA_FIELD_X = "x";// 数据域左上角X坐标
	public static String DATA_FIELD_Y = "y";// 数据域左上角Y坐标
	public static String DATA_FIELD_FONT_FAMILY = "ff";// 数据域字体名称
	public static String DATA_FIELD_FONT_WEIGHT = "fw";// 数据域字体类型
	public static String DATA_FIELD_FONT_SIZE = "fs";// 数据域字体大小
	public static String DATA_FIELD_FONT_COLOR = "fc";// 数据域字体颜色
	public static String DATA_FIELD_INDEX = "index"; // 所属曲线
	public static final int DATA_FIELD_INDEX_NULL = -1;

	public static String DATA_FIELD_TYPE = "type";// 数据域类型
	public static final int DATA_FIELD_TYPE_RDB = 0; // 关系库
	public static final int DATA_FIELD_TYPE_RTDB = 1; // 实时库
	public static final int DATA_FIELD_TYPE_VIEW_DATE = 2; // 曲线浏览时日期
	public static final int DATA_FIELD_TYPE_DATE = 3; // 曲线日期
	public static final int DATA_FIELD_TYPE_MAX = 4; // 曲线最大值
	public static final int DATA_FIELD_TYPE_MAX_TIME = 5; // 曲线最大值时间
	public static final int DATA_FIELD_TYPE_MIN = 6; // 曲线最小值
	public static final int DATA_FIELD_TYPE_MIN_TIME = 7; // 曲线最小值时间
	public static final int DATA_FIELD_TYPE_AVE = 8; // 曲线平均值
	public static final int DATA_FIELD_TYPE_CURRENT = 9; // 曲线最新值
	public static final int DATA_FIELD_TYPE_INTEGRAL_ELECTRICITY_QUANTITY = 10; // 积分电量
	public static final int DATA_FIELD_TYPE_RTNCON_DAY_MR = 11;// 日平均遥测估计合格率
	public static final int DATA_FIELD_TYPE_RTNCON_DAY_CN = 12;// 状态估计日计算次数
	public static final int DATA_FIELD_TYPE_RTNCON_DAY_SN = 13;// 状态估计日收敛次数
	public static final int DATA_FIELD_TYPE_RTNCON_DAY_SR = 14;	// 状态估计日收敛率
	public static final int DATA_FIELD_TYPE_RTNCON_MONTH_MR = 15;// 月平均遥测估计合格率
	public static final int DATA_FIELD_TYPE_RTNCON_MONTH_CN = 16;// 状态估计月计算次数
	public static final int DATA_FIELD_TYPE_RTNCON_MONTH_SN = 17;// 状态估计月收敛次数
	public static final int DATA_FIELD_TYPE_RTNCON_MONTH_SR = 18;	// 状态估计月收敛率
	public static final int DATA_FIELD_TYPE_RTNCON_YEAR_MR = 19;// 年平均遥测估计合格率
	public static final int DATA_FIELD_TYPE_RTNCON_YEAR_CN = 20;	// 状态估计年计算次数
	public static final int DATA_FIELD_TYPE_RTNCON_YEAR_SN = 21;	// 状态估计年收敛次数
	public static final int DATA_FIELD_TYPE_RTNCON_YEAR_SR = 22;// 状态估计年收敛率
	public static final int DATA_FIELD_TYPE_BIG_RTNCON_DAY_MR = 23;// 大模日平均遥测估计合格率
	public static final int DATA_FIELD_TYPE_BIG_RTNCON_DAY_CN = 24;// 大模状态估计日计算次数
	public static final int DATA_FIELD_TYPE_BIG_RTNCON_DAY_SN = 25;// 大模状态估计日收敛次数
	public static final int DATA_FIELD_TYPE_BIG_RTNCON_DAY_SR = 26;// 大模状态估计日收敛率
	public static final int DATA_FIELD_TYPE_BIG_RTNCON_MONTH_MR = 27;// 大模月平均遥测估计合格率
	public static final int DATA_FIELD_TYPE_BIG_RTNCON_MONTH_CN = 28;	// 大模状态估计月计算次数
	public static final int DATA_FIELD_TYPE_BIG_RTNCON_MONTH_SN = 29;// 大模状态估计月收敛次数
	public static final int DATA_FIELD_TYPE_BIG_RTNCON_MONTH_SR = 30;// 大模状态估计月收敛率
	public static final int DATA_FIELD_TYPE_BIG_RTNCON_YEAR_MR = 31;	// 大模年平均遥测估计合格率
	public static final int DATA_FIELD_TYPE_BIG_RTNCON_YEAR_CN = 32;// 大模状态估计年计算次数
	public static final int DATA_FIELD_TYPE_BIG_RTNCON_YEAR_SN = 33;// 大模状态估计年收敛次数
	public static final int DATA_FIELD_TYPE_BIG_RTNCON_YEAR_SR = 34;	// 大模状态估计年收敛率

	public static String DATA_FIELD_DECIMAL = "decmial";// 精度
	public static String DATA_FIELD_UNIT = "unit";// 单位
	// 数据域内容表示具体的数据域描述,如实时库包含表、域、记录号等信息,关系库包含SQL语句及是否与曲线id关联等信息
	public static String DATA_FIELD_CONTENT = "content";

	/** 焦点域属性 FocusField */
	public static String FOCUS_FIELD_X = "x";// 焦点域左上角X坐标
	public static String FOCUS_FIELD_Y = "y";// 焦点域左上角Y坐标
	public static String FOCUS_FIELD_FONT_FAMILY = "ff";// 焦点域字体名称
	public static String FOCUS_FIELD_FONT_WEIGHT = "fw";// 焦点域字体类型
	public static String FOCUS_FIELD_FONT_SIZE = "fs";// 焦点域字体大小
	public static String FOCUS_FIELD_FONT_COLOR = "fc";// 焦点域字体颜色

	public static String FOCUS_FIELD_TYPE = "type";// 焦点域类型
	public static final int FOCUS_FIELD_TYPE_JAVA = 0;// 调用ava方法
	public static final int FOCUS_FIELD_TYPE_SHELL = 1;// 调用shell

	public static String FOCUS_FIELD_CONTENT = "content"; // 焦点内容，表示java方法或脚本命令的具体内容
	public static String FOCUS_FIELD_TEXT = "text"; // 焦点域显示文本

	/** 曲线实体属性 Entity */
	public static String ENTITY_LINE_COLOR = "lc";// 曲线颜色
	public static String ENTITY_LINE_WIDTH = "lw";// 曲线线宽
	public static String ENTITY_LINE_STYLE = "ls";// 曲线线型

	public static String ENTITY_DRAW_STRATEGY = "ds";// 曲线画法
	public static final int ENTITY_DRAW_STRATEGY_NULL = 0;// 无
	public static final int ENTITY_DRAW_STRATEGY_LINE = 1;// 直线
	public static final int ENTITY_DRAW_STRATEGY_BIAS = 2;// 偏差线
	public static final int ENTITY_DRAW_STRATEGY_LADDER = 3;// 台阶线
	public static final int ENTITY_DRAW_STRATEGY_DOT = 4;// 点

	public static String ENTITY_TITLE = "title";// 曲线名称
	public static String ENTITY_LEGEND_SHOW = "legend";// 是否显示图例
	public static String ENTITY_AUTO = "auto";// 是否自动设置曲线标题
	public static String ENTITY_INDEX = "index";// 曲线序号

	public static String ENTITY_AXIS_Y_TYPE = "y_type";// 所属纵坐标

	/** 曲线动态属性 Dynamic */
	public static String DYNAMIC_DATASOURCE_TYPE = "type";// 曲线数据源类型,以历史采样定义曲线类型为准
	public static String DYNAMIC_DATASOURCE_TYPE_XY = "100";// 南瑞XY曲线
	
	public static String DYNAMIC_APP = "app"; // 应用号
	public static String DYNAMIC_X_APP = "x_app"; // 应用号
	public static String DYNAMIC_INDEX = "index";// 曲线序号
	public static String DYNAMIC_KEYID = "keyid"; // 设备关键字
	public static String DYNAMIC_ABSTIME = "abstime";// 是否使用绝对时间
//	public static String DYNAMIC_CONTEXT_NAME = "contextName";// 是否使用绝对时间
	public static String DYNAMIC_X_KEY_ID = "x_keyid";// 是否使用绝对时间

	public static String DYNAMIC_TIME_START_TYPE = "start_type"; // 开始类型
	public static String DYNAMIC_TIME_START_YEAR = "start_year";// 启始年
	public static String DYNAMIC_TIME_START_MONTH = "start_month";// 启始月
	public static String DYNAMIC_TIME_START_DAY = "start_day";// 启始日
	public static String DYNAMIC_TIME_START_HOUR = "start_hour";// 启始小时
	public static String DYNAMIC_TIME_START_MINUTE = "start_minute";// 启始分钟
	public static String DYNAMIC_TIME_START_SECOND = "start_second";// 启始秒
	public static String DYNAMIC_TIME_START_WEEK = "start_week";// 启始周
	public static String DYNAMIC_TIME_START_WEEK_DAY = "start_week_day";// 启始周日期
	public static String DYNAMIC_TIME_END_TYPE = "end_type"; // 结束类型
	public static String DYNAMIC_TIME_END_YEAR = "end_year";// 结束年
	public static String DYNAMIC_TIME_END_MONTH = "end_month";// 结束月
	public static String DYNAMIC_TIME_END_DAY = "end_day";// 结束日期
	public static String DYNAMIC_TIME_END_HOUR = "end_hour";// 结束小时
	public static String DYNAMIC_TIME_END_MINUTE = "end_minute";// 结束分钟
	public static String DYNAMIC_TIME_END_SECOND = "end_second";// 结束秒
	public static String DYNAMIC_TIME_END_WEEK = "end_week";// 结束周
	public static String DYNAMIC_TIME_END_WEEK_DAY = "end_week_day";// 结束周日期

	public static String DYNAMIC_DISTANT = "distant";// 距当前时间间
	public static String DYNAMIC_DISTANT_UNIT = "distantunit";// 距当前时间单位
	public static String DYNAMIC_LEN = "len";// 相对时间时的跨度
	public static String DYNAMIC_LEN_UNIT = "len_unit";// 相对时间的跨度单位

	public static String DYNAMIC_MODE = "mode";// 采样值统计方式,参照历史采样接口
	public static final int DYNAMIC_MODE_MAX = 1;// 最大值
	public static final int DYNAMIC_MODE_MIN = 2;// 最小值
	public static final int DYNAMIC_MODE_AVE = 3;// 平均值
	public static final int DYNAMIC_MODE_BEGIN = 4;// 起始值
	public static final int DYNAMIC_MODE_END = 5;// 终止值

	public static String DYNAMIC_PERIOD = "period";// 采用周期
	public static String DYNAMIC_QUERY_UNIT = "query_unit";// 采用周期单位

	public static final int DYNAMIC_DIRECTION_HORIZONTIAL = 0;// 横向
	public static final int DYNAMIC_DIRECTION_VERTICAL = 1;// 纵向
	public static final String DYNAMIC_X_DIRECTION = "x_direction"; // X轴采样点数方向
	public static final String DYNAMIC_Y_DIRECTION = "y_direction"; // Y轴采样点数方向
	public static final String DYNAMIC_DECIMAL= "decimal"; // Y轴采样点数方向
	public static final String DYNAMIC_Y_TYPE= "y_type"; //所属纵坐标

	public static final int DYNAMIC_X_PACE_1 = 1; // 每个点都取
	public static final int DYNAMIC_X_PACE_2 = 2; // 隔1个点取
	public static final int DYNAMIC_X_PACE_3 = 3; // 隔2个点取
	public static String DYNAMIC_X_PACE = "x_pace"; // X轴采样方式
	public static String DYNAMIC_Y_PACE = "y_pace"; // Y轴采样方式

	public static String DYNAMIC_POINT = "point";// 采样点数
	public static String DYNAMIC_POINT_MODE = "pointmode";// 数据源方式
	public static final int DYNAMIC_POINT_MODE_0 = 0;// 点等距
	public static final int DYNAMIC_POINT_MODE_1 = 1;// 自然等距
	public static final int DYNAMIC_POINT_MODE_2 = 2;// 点等距（时间Tip）

	/** NARI 时间格式变量 */
	// 秒
	public static final int THIS_SECOND = -1;
	public static final int LAST_SECOND = -2;
	public static final int LAST_TWO_SECOND = -3;
	public static final int NEXT_ONE_SECOND = 60;
	public static final int NEXT_TWO_SECOND = 61;
	// 分钟
	public static final int THIS_MINUTE = -1;// 本分
	public static final int LAST_MINUTE = -2; // 前一分
	public static final int LAST_TWO_MINUTE = -3;// 前两分
	public static final int LAST_FIVE_MINUTE = -6; // 前五分
	public static final int NEXT_ONE_MINUTE = 60;// 下一分
	public static final int NEXT_TWO_MINUTE = 61;// 下两分
	public static final int NEXT_FIVE_MINUTE = 64; // 下五分钟
	// 小时
	public static final int THIS_HOUR = -1;
	public static final int LAST_HOUR = -2;
	public static final int NEXT_ONE_HOUR = 24;
	public static final int NEXT_TWO_HOUR = 25;
	// 日
	public static final int THIS_DAY = 0;
	public static final int LAST_DAY = -1;
	public static final int LAST_TWO_DAY = -2;
	public static final int LAST_THREE_DAY = -3;
	public static final int NEXT_DAY = 32;// 明日
	public static final int NEXT_TWO_DAY = 33;// 后日
	// 月
	public static final int THIS_MONTH = 0;
	public static final int LAST_MONTH = -1;
	public static final int LAST_TWO_MONTH = -2;
	public static final int LAST_THREE_MONTH = -3;
	public static final int NEXT_ONE_MONTH = 13; // 下月
	public static final int NEXT_TWO_MONTH = 14;// 下下月
	// 年
	public static final int THIS_YEAR = 0; // 本年
	public static final int LAST_YEAR = -1; // 上一年
	public static final int LAST_TWO_YEAR = -2; // 上两年
	public static final int LAST_THREE_YEAR = -3; // 上三年
	public static final int LAST_FOUR_YEAR = -4; // 上四年
	public static final int NEXT_ONE_YEAR = 1; // 下一年
	public static final int NEXT_TWO_YEAR = 2; // 下二年
	// 周
	public static final int THIS_WEEK = 0;// 本周
	public static final int MON = 1;
	public static final int TUES = 2;
	public static final int WED = 3;
	public static final int TURS = 4;
	public static final int FRIDAY = 5;
	public static final int SAT = 6;
	public static final int SUNDAY = 0;
	public static final int THIS_WEEKDAY = 10;// 今日
	public static final int LAST_WEEKDAY = -1;// 昨日
	public static final int LAST_TWO_WEEKDAY = -2;// 前日
	public static final int NEXT_ONE_WEEKDAY = 7;// 明日
	public static final int NEXT_TWO_WEEKDAY = 8;// 后日

	public static String LINE_STYLE_NULL_STR = "无";  //  @jve:decl-index=0:
	public static String LINE_STYLE_SOLID_STR = "实线";  //  @jve:decl-index=0:
	public static String LINE_STYLE_DASH_STR = "虚线";  //  @jve:decl-index=0:
	public static String LINE_STYLE_DOT_STR = "点线";  //  @jve:decl-index=0:
	public static String LINE_STYLE_DASH_DOT_STR = "点划线-.-";  //  @jve:decl-index=0:
	public static String LINE_STYLE_DASH_DOT_DOT_STR = "点划线-..-";  //  @jve:decl-index=0:
	
	public static void main(String[] args) {
		
	}
}
