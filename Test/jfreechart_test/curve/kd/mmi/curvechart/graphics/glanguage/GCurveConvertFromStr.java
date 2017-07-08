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

	public static final int COLOR_WITHE = -1; // ��ɫ
	public static final int COLOR_BLACK = 0; // ��ɫ

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
	public static final int DEFAULT_TIME_INT_VALUE = 0;// Ĭ��ȡ��ʱ��ֵ
	public static final float DEFAULT_MAX_VALUE = 100f;// Ĭ�����ֵ
	public static final float DEFAULT_MIN_VALUE = 0f;// Ĭ����Сֵ
	public static final int DEFAULT_DECIMAL_VALUE = 2;// Ĭ��С������

	/** ͨ������ Curve */
	public static String CHART_X = "x"; // ģ�����Ͻ�X����ֵ
	public static String CHART_Y = "y"; // ģ�����Ͻ�Y����ֵ
	public static String CHART_HEIGHT = "h"; // ģ��߶�
	public static String CHART_WIDTH = "w"; // ģ����
	public static String CHART_BACKGROUND_COLOR = "bgc";// ģ�屳����ɫ

	public static String CHART_BACKGROUND_FILLPATTERN = "bgm";// �������ģʽ
	public static final int CHART_BACKGROUND_FILL_SINGLE = 0;// ��ɫ
	public static final int CHART_BACKGROUND_FILL_TRANSPARENT = 1;// ͸��
	public static final int CHART_BACKGROUND_FILL_PIC = 2;// ����ͼƬ(��Ƭ)

	public static String CHART_BACKGROUND_PIC = "pic_url"; // ����ͼƬ·������
	public static String GRID_BACKGROUND_COLOR = "cgc";// ���񱳾���ɫ(����ɫ)
	public static String TITLE_SHOW = "title_show";// ��ʾ����
	public static String TITLE_CONTENT = "title_content";// ��������

	public static String REFRESH_INTERVAL = "interval";// ˢ�¼��,��λ��
	public static final int REFRESH_DEFAULT_PERIOD = 60;// Ĭ��ˢ������60��

	public static String MULTI_AXIS_TYPE = "multi_axis_type";// ��������
	public static final int MULTI_AXIS_TYPE_SINGLE = 0;// ����
	public static final int MULTI_AXIS_TYPE_DOUBLE = 1;// ˫��
	public static final int MULTI_AXIS_TYPE_MULTI = 2;// ����

	/** ���񱳾� Grid */
	public static final int LINE_DEFAULT_WIDTH = 1; // Ĭ���߿�
	public static final int LINE_STYLE_NULL = 0; // ��
	public static final int LINE_STYLE_SOLID = 1; // ʵ��
	public static final int LINE_STYLE_DASH = 2; // ���� 4-2
	public static final int LINE_STYLE_DOT = 3; // ���� 1-2
	public static final int LINE_STYLE_DASH_DOT = 4; // ���� 4-2-1-2
	public static final int LINE_STYLE_DASH_DOT_DOT = 5; // ���� 4-2-1-2-1-2

	public static String MAIN_GRID_X = "main_grid_x";// ���������Ͻ�X����
	public static String MAIN_GRID_Y = "main_grid_y";// ���������Ͻ�Y����
	public static String MAIN_GRID_WIDTH = "main_grid_w";// ��������
	public static String MAIN_GRID_HEIGHT = "main_grid_h";// ������߶�
	public static String MAIN_GRID_COLOR = "main_grid_c";// ��������ɫ
	public static String MAIN_GRID_LINE_WIDTH = "main_grid_lw";// �������߿�
	public static String MAIN_GRID_LINE_STYLE = "main_grid_ls";// ����������
	public static String SUB_GRID_COLOR = "sub_grid_c";// ��������ɫ
	public static String SUB_GRID_LINE_WIDTH = "sub_grid_lw";// �������߿�
	public static String SUB_GRID_LINE_STYLE = "sub_grid_ls";// ����������
	public static String MAIN_GRID_X_SHOW = "main_grid_x_show";// ��ʾX����������
	public static String SUB_GRID_X_SHOW = "sub_grid_x_show";// ��ʾX��������
	public static String MAIN_GRID_Y_SHOW = "main_grid_y_show";// ��ʾY����������
	public static String SUB_GRID_Y_SHOW = "sub_grid_y_show";// ��ʾY��������

	/** ���������� X-axis */
	public static String AXIS_X_SCALE_TYPE = "scale";// ��������ʾ����
	public static final int AXIS_X_SCALE_TYPE_NUM = 0; // ��������
	public static final int AXIS_X_SCALE_TYPE_TIME = 1; // ʱ������
	public static final int AXIS_X_SCALE_TYPE_RTDB = 2; // ʵʱ��
	public static final int AXIS_X_SCALE_TYPE_RDB = 3; // ��ϵ��

	public static String AXIS_X_SHOW = "show";// ���������Ƿ���ʾ
	public static String AXIS_X_RUN = "run";// �������Ƿ��ܶ�
	public static String AXIS_X_RUN_PACE = "runpace";// �ܶ�����
	public static String AXIS_X_PACE = "pace";// �����경��
	public static String AXIS_X_PACE_UNIT = "unit";// �����경����λ
	public static String AXIS_X_PREPARATORY_PERCENT = "percent";// ������Ԥ���ٷֱ�
	public static String AXIS_X_MAIN_SPACE = "mainspace";// �����������
	public static String AXIS_X_SUB_SPACE = "subspace";// ������μ��
	public static String AXIS_X_ISPOINT = "ispoint";// �Ƿ��յ�������
	public static String AXIS_X_POINT = "point";// ��������

	public static String AXIS_X_POINT_MODE = "pointmode";// ����Դ��ʽ
	public static final int AXIS_X_POINT_MODE_EQUIDISTANCE = 0;// ��Ⱦ�
	public static final int AXIS_X_POINT_MODE_RAW = 1;// ��Ȼ����
	public static final int AXIS_X_POINT_MODE_EQUIDISTANCE_TIME = 2; // ��Ⱦ�(ʱ��Tip)

	public static String AXIS_X_POINT_DIRECTION = "pointdirection";// ������������
	public static final int AXIS_X_POINT_DIRECTION_HORIZONTAL = 0;// ����
	public static final int AXIS_X_POINT_DIRECTION_VERTICAL = 1;// ����

	public static String AXIS_X_POINT_SPACE = "pointspace";// ����������
	public static String AXIS_X_FONT_FAMILY = "ff";// ��������̶���������
	public static String AXIS_X_FONT_WEIGHT = "fw";// ��������̶���������
	public static String AXIS_X_FONT_SIZE = "fs";// ��������̶������С
	public static String AXIS_X_FONT_COLOR = "fc";// ��������̶�������ɫ
	/** ʱ������ */
	public static String AXIS_X_TIME_FORMAT = "format";// ��߸�ʽ
	public static final int AXIS_X_TIME_FORMAT_SS = 0; // ��
	public static final int AXIS_X_TIME_FORMAT_MM = 1; // ����
	public static final int AXIS_X_TIME_FORMAT_HH = 2; // Сʱ
	public static final int AXIS_X_TIME_FORMAT_MM_SS = 3; // ����:�루10��00��
	public static final int AXIS_X_TIME_FORMAT_HH_MM = 4; // Сʱ:���ӣ�09��30��
	public static final int AXIS_X_TIME_FORMAT_YYYY_MM_DD = 5; // ��-��-�գ�2010-03-02��
	public static final int AXIS_X_TIME_FORMAT_YYYY_MM = 6; // ��-�£�2010-03��
	public static final int AXIS_X_TIME_FORMAT_YYYY = 7; // �꣨2010��
	public static final int AXIS_X_TIME_FORMAT_MM_DD = 8; // ��-�գ�03-02��
	public static final int AXIS_X_TIME_FORMAT_DD = 9; // �գ�02��
	public static String AXIS_X_TIME_FORMAT_SS_STR = "��";
	public static String AXIS_X_TIME_FORMAT_MM_STR = "��";
	public static String AXIS_X_TIME_FORMAT_HH_STR = "ʱ";
	public static String AXIS_X_TIME_FORMAT_MM_SS_STR = "��:��";
	public static String AXIS_X_TIME_FORMAT_HH_MM_STR = "ʱ:��";
	public static String AXIS_X_TIME_FORMAT_YYYY_MM_DD_STR = "��-��-��";
	public static String AXIS_X_TIME_FORMAT_YYYY_MM_STR = "��-��";
	public static String AXIS_X_TIME_FORMAT_YYYY_STR = "��";
	public static String AXIS_X_TIME_FORMAT_MM_DD_STR = "��-��";
	public static String AXIS_X_TIME_FORMAT_DD_STR = "��";  //  @jve:decl-index=0:
	public static String AXIS_X_RUN_UNIT = "rununit";
	
	public static String AXIS_X_TIME_START = "start";// ��ʼʱ��
	public static String AXIS_X_TIME_END = "end";// ����ʱ��
	public static String AXIS_X_TIME_IS_ABS = "abs_time"; // �Ƿ����ʱ��
	public static String AXIS_X_TIME_IS_INT = "int_time"; // �̶�ʱ���Ƿ�ȡ��
	public static String AXIS_X_TIME_INT_TIME_VALUE = "int_time_value"; // �̶�ʱ���Ƿ�ȡ��
	public static String AXIS_X_TIME_SPAN = "span";// ������ʱ����

	public static String AXIS_X_TIME_UNIT = "unit";// ʱ������λ
	public static final int AXIS_X_TIME_UNIT_SECOND = 0;
	public static final int AXIS_X_TIME_UNIT_MINUTE = 1;
	public static final int AXIS_X_TIME_UNIT_HOUR = 2;
	public static final int AXIS_X_TIME_UNIT_DAY = 3;
	public static final int AXIS_X_TIME_UNIT_MONTH = 4;
	public static final int AXIS_X_TIME_UNIT_YEAR = 5;
	public static final int AXIS_X_TIME_UNIT_WEEK = 6;
	public static final int AXIS_X_TIME_UNIT_QUARTER = 7;
	public static String AXIS_X_TIME_UNIT_SECOND_STR = "��";
	public static String AXIS_X_TIME_UNIT_MINUTE_STR = "��";
	public static String AXIS_X_TIME_UNIT_HOUR_STR = "ʱ";
	public static String AXIS_X_TIME_UNIT_DAY_STR = "��";
	public static String AXIS_X_TIME_UNIT_MONTH_STR = "��";
	public static String AXIS_X_TIME_UNIT_YEAR_STR = "��";
	public static String AXIS_X_TIME_UNIT_WEEK_STR = "��";
	public static String AXIS_X_TIME_UNIT_QUARTER_STR = "����";

	/** �������� */
	public static String AXIS_X_NUMBER_MAX = "max";// ���������ֵ
	public static String AXIS_X_NUMBER_MIN = "min";// ��������Сֵ
	public static String AXIS_X_NUMBER_DECIMAL = "decimal";// ������С��λ��

	/** ���������� Y-axis */
	public static String AXIS_Y_AUTO_SCALE = "auto";// �������Ƿ�����Ӧ
	public static String AXIS_Y_SHOW = "show";// �ݱ���Ƿ���ʾ

	public static String AXIS_Y_CNUM = "cnum"; //��������
	
	public static String MULTI_AXIS_DEF = "multi_axis_def";// ����������궨��,0Ĭ��Ϊ��������,����Ϊ��������,ż��Ϊ��������
	public static final int MULTI_AXIS_SINGLE = 0; // ����
	public static final int MULTI_AXIS_LEFT_1 = 1; // ��1
	public static final int MULTI_AXIS_RIGHT_1 = 2; // ��1
	public static final int MULTI_AXIS_LEFT_2 = 3; // ��2
	public static final int MULTI_AXIS_RIGHT_2 = 4; // ��2

	public static String AXIS_Y_MAIN_SPACE = "mainspace";// X�����������
	public static String AXIS_Y_SUB_SPACE = "subspace";// X������μ��
	public static String AXIS_Y_NUMBER_MAX = "max";// ���������ֵ
	public static String AXIS_Y_NUMBER_MIN = "min";// ��������Сֵ
	public static String AXIS_Y_NUMBER_DECIMAL = "decimal";// ������С��λ��
	public static String AXIS_Y_MAX_RATE = "maxrate";// ���������ֵ�ϸ�����
	public static String AXIS_Y_MIN_RATE = "minrate";// ��������Сֵ�ϸ�����
	public static String AXIS_Y_IS_INTEGER = "isinteger";// ����������̶��Ƿ����
	public static String AXIS_Y_AUTO_WITH_LIMIT = "autowithlimit";// ����������̶��Ƿ���ֵ������Ӧ
	public static String AXIS_Y_FONT_FAMILY = "ff";// ��������̶���������
	public static String AXIS_Y_FONT_WEIGHT = "fw";// ��������̶���������
	public static String AXIS_Y_FONT_SIZE = "fs";// ��������̶������С
	public static String AXIS_Y_FONT_COLOR = "fc";// ��������̶�������ɫ
	public static final String AXIS_Y_PACE = "pace";//��߲���

	/** ͼ������ Legend */
	public static String LEGEND_X = "x";// ͼ�����Ͻ�X����
	public static String LEGEND_Y = "y";// ͼ�����Ͻ�Y����
	public static String LEGEND_FONT_FAMILY = "ff";// ͼ����������
	public static String LEGEND_FONT_WEIGHT = "fw";// ͼ����������
	public static String LEGEND_FONT_SIZE = "fs";// ͼ�������С
	public static String LEGEND_FONT_COLOR = "fc";// ͼ�������С

	/** ��ҳ������ TurnOverField */
	public static String TURNOVER_FIELD_X = "x";// ��ҳ�����Ͻ�X����
	public static String TURNOVER_FIELD_Y = "y";// ��ҳ�����Ͻ�Y����
	public static String TURNOVER_FIELD_HEIGHT = "h";// ��ҳ��߶�
	public static String TURNOVER_FIELD_WIDTH = "w";// ��ҳ����
	public static String TURNOVER_FIELD_COLOR = "color";// ��ҳ����ɫ

	public static String TURNOVER_FIELD_TYPE = "type";// ��ҳ������
	public static final int TURNOVER_FIELD_TYPE_PRE = 0;// ǰ��
	public static final int TURNOVER_FIELD_TYPE_NEXT = 1;// ��

	/** ���������� TextField */
	public static String TEXT_FIELD_X = "x";// ���������Ͻ�X����
	public static String TEXT_FIELD_Y = "y";// ���������Ͻ�Y����
	public static String TEXT_FIELD_HEIGHT = "h";// ������߶�
	public static String TEXT_FIELD_WIDTH = "w";// ��������
	public static String TEXT_FIELD_FONT_FAMILY = "ff";// ��������������
	public static String TEXT_FIELD_FONT_WEIGHT = "fw";// ��������������
	public static String TEXT_FIELD_FONT_SIZE = "fs";// �����������С
	public static String TEXT_FIELD_FONT_COLOR = "fc";// ������������ɫ

	public static String TEXT_FIELD_TYPE = "type";// ����������
	public static final int TEXT_FIELD_TYPE_TITLE = 0;// ����
	public static final int TEXT_FIELD_TYPE_OTHER = 255;// ��������

	public static String TEXT_FIELD_CONTENT = "content"; // ����������

	/** ���������� DataField */
	public static String DATA_FIELD_X = "x";// ���������Ͻ�X����
	public static String DATA_FIELD_Y = "y";// ���������Ͻ�Y����
	public static String DATA_FIELD_FONT_FAMILY = "ff";// ��������������
	public static String DATA_FIELD_FONT_WEIGHT = "fw";// ��������������
	public static String DATA_FIELD_FONT_SIZE = "fs";// �����������С
	public static String DATA_FIELD_FONT_COLOR = "fc";// ������������ɫ
	public static String DATA_FIELD_INDEX = "index"; // ��������
	public static final int DATA_FIELD_INDEX_NULL = -1;

	public static String DATA_FIELD_TYPE = "type";// ����������
	public static final int DATA_FIELD_TYPE_RDB = 0; // ��ϵ��
	public static final int DATA_FIELD_TYPE_RTDB = 1; // ʵʱ��
	public static final int DATA_FIELD_TYPE_VIEW_DATE = 2; // �������ʱ����
	public static final int DATA_FIELD_TYPE_DATE = 3; // ��������
	public static final int DATA_FIELD_TYPE_MAX = 4; // �������ֵ
	public static final int DATA_FIELD_TYPE_MAX_TIME = 5; // �������ֵʱ��
	public static final int DATA_FIELD_TYPE_MIN = 6; // ������Сֵ
	public static final int DATA_FIELD_TYPE_MIN_TIME = 7; // ������Сֵʱ��
	public static final int DATA_FIELD_TYPE_AVE = 8; // ����ƽ��ֵ
	public static final int DATA_FIELD_TYPE_CURRENT = 9; // ��������ֵ
	public static final int DATA_FIELD_TYPE_INTEGRAL_ELECTRICITY_QUANTITY = 10; // ���ֵ���
	public static final int DATA_FIELD_TYPE_RTNCON_DAY_MR = 11;// ��ƽ��ң����ƺϸ���
	public static final int DATA_FIELD_TYPE_RTNCON_DAY_CN = 12;// ״̬�����ռ������
	public static final int DATA_FIELD_TYPE_RTNCON_DAY_SN = 13;// ״̬��������������
	public static final int DATA_FIELD_TYPE_RTNCON_DAY_SR = 14;	// ״̬������������
	public static final int DATA_FIELD_TYPE_RTNCON_MONTH_MR = 15;// ��ƽ��ң����ƺϸ���
	public static final int DATA_FIELD_TYPE_RTNCON_MONTH_CN = 16;// ״̬�����¼������
	public static final int DATA_FIELD_TYPE_RTNCON_MONTH_SN = 17;// ״̬��������������
	public static final int DATA_FIELD_TYPE_RTNCON_MONTH_SR = 18;	// ״̬������������
	public static final int DATA_FIELD_TYPE_RTNCON_YEAR_MR = 19;// ��ƽ��ң����ƺϸ���
	public static final int DATA_FIELD_TYPE_RTNCON_YEAR_CN = 20;	// ״̬������������
	public static final int DATA_FIELD_TYPE_RTNCON_YEAR_SN = 21;	// ״̬��������������
	public static final int DATA_FIELD_TYPE_RTNCON_YEAR_SR = 22;// ״̬������������
	public static final int DATA_FIELD_TYPE_BIG_RTNCON_DAY_MR = 23;// ��ģ��ƽ��ң����ƺϸ���
	public static final int DATA_FIELD_TYPE_BIG_RTNCON_DAY_CN = 24;// ��ģ״̬�����ռ������
	public static final int DATA_FIELD_TYPE_BIG_RTNCON_DAY_SN = 25;// ��ģ״̬��������������
	public static final int DATA_FIELD_TYPE_BIG_RTNCON_DAY_SR = 26;// ��ģ״̬������������
	public static final int DATA_FIELD_TYPE_BIG_RTNCON_MONTH_MR = 27;// ��ģ��ƽ��ң����ƺϸ���
	public static final int DATA_FIELD_TYPE_BIG_RTNCON_MONTH_CN = 28;	// ��ģ״̬�����¼������
	public static final int DATA_FIELD_TYPE_BIG_RTNCON_MONTH_SN = 29;// ��ģ״̬��������������
	public static final int DATA_FIELD_TYPE_BIG_RTNCON_MONTH_SR = 30;// ��ģ״̬������������
	public static final int DATA_FIELD_TYPE_BIG_RTNCON_YEAR_MR = 31;	// ��ģ��ƽ��ң����ƺϸ���
	public static final int DATA_FIELD_TYPE_BIG_RTNCON_YEAR_CN = 32;// ��ģ״̬������������
	public static final int DATA_FIELD_TYPE_BIG_RTNCON_YEAR_SN = 33;// ��ģ״̬��������������
	public static final int DATA_FIELD_TYPE_BIG_RTNCON_YEAR_SR = 34;	// ��ģ״̬������������

	public static String DATA_FIELD_DECIMAL = "decmial";// ����
	public static String DATA_FIELD_UNIT = "unit";// ��λ
	// ���������ݱ�ʾ���������������,��ʵʱ��������򡢼�¼�ŵ���Ϣ,��ϵ�����SQL��估�Ƿ�������id��������Ϣ
	public static String DATA_FIELD_CONTENT = "content";

	/** ���������� FocusField */
	public static String FOCUS_FIELD_X = "x";// ���������Ͻ�X����
	public static String FOCUS_FIELD_Y = "y";// ���������Ͻ�Y����
	public static String FOCUS_FIELD_FONT_FAMILY = "ff";// ��������������
	public static String FOCUS_FIELD_FONT_WEIGHT = "fw";// ��������������
	public static String FOCUS_FIELD_FONT_SIZE = "fs";// �����������С
	public static String FOCUS_FIELD_FONT_COLOR = "fc";// ������������ɫ

	public static String FOCUS_FIELD_TYPE = "type";// ����������
	public static final int FOCUS_FIELD_TYPE_JAVA = 0;// ����ava����
	public static final int FOCUS_FIELD_TYPE_SHELL = 1;// ����shell

	public static String FOCUS_FIELD_CONTENT = "content"; // �������ݣ���ʾjava������ű�����ľ�������
	public static String FOCUS_FIELD_TEXT = "text"; // ��������ʾ�ı�

	/** ����ʵ������ Entity */
	public static String ENTITY_LINE_COLOR = "lc";// ������ɫ
	public static String ENTITY_LINE_WIDTH = "lw";// �����߿�
	public static String ENTITY_LINE_STYLE = "ls";// ��������

	public static String ENTITY_DRAW_STRATEGY = "ds";// ���߻���
	public static final int ENTITY_DRAW_STRATEGY_NULL = 0;// ��
	public static final int ENTITY_DRAW_STRATEGY_LINE = 1;// ֱ��
	public static final int ENTITY_DRAW_STRATEGY_BIAS = 2;// ƫ����
	public static final int ENTITY_DRAW_STRATEGY_LADDER = 3;// ̨����
	public static final int ENTITY_DRAW_STRATEGY_DOT = 4;// ��

	public static String ENTITY_TITLE = "title";// ��������
	public static String ENTITY_LEGEND_SHOW = "legend";// �Ƿ���ʾͼ��
	public static String ENTITY_AUTO = "auto";// �Ƿ��Զ��������߱���
	public static String ENTITY_INDEX = "index";// �������

	public static String ENTITY_AXIS_Y_TYPE = "y_type";// ����������

	/** ���߶�̬���� Dynamic */
	public static String DYNAMIC_DATASOURCE_TYPE = "type";// ��������Դ����,����ʷ����������������Ϊ׼
	public static String DYNAMIC_DATASOURCE_TYPE_XY = "100";// ����XY����
	
	public static String DYNAMIC_APP = "app"; // Ӧ�ú�
	public static String DYNAMIC_X_APP = "x_app"; // Ӧ�ú�
	public static String DYNAMIC_INDEX = "index";// �������
	public static String DYNAMIC_KEYID = "keyid"; // �豸�ؼ���
	public static String DYNAMIC_ABSTIME = "abstime";// �Ƿ�ʹ�þ���ʱ��
//	public static String DYNAMIC_CONTEXT_NAME = "contextName";// �Ƿ�ʹ�þ���ʱ��
	public static String DYNAMIC_X_KEY_ID = "x_keyid";// �Ƿ�ʹ�þ���ʱ��

	public static String DYNAMIC_TIME_START_TYPE = "start_type"; // ��ʼ����
	public static String DYNAMIC_TIME_START_YEAR = "start_year";// ��ʼ��
	public static String DYNAMIC_TIME_START_MONTH = "start_month";// ��ʼ��
	public static String DYNAMIC_TIME_START_DAY = "start_day";// ��ʼ��
	public static String DYNAMIC_TIME_START_HOUR = "start_hour";// ��ʼСʱ
	public static String DYNAMIC_TIME_START_MINUTE = "start_minute";// ��ʼ����
	public static String DYNAMIC_TIME_START_SECOND = "start_second";// ��ʼ��
	public static String DYNAMIC_TIME_START_WEEK = "start_week";// ��ʼ��
	public static String DYNAMIC_TIME_START_WEEK_DAY = "start_week_day";// ��ʼ������
	public static String DYNAMIC_TIME_END_TYPE = "end_type"; // ��������
	public static String DYNAMIC_TIME_END_YEAR = "end_year";// ������
	public static String DYNAMIC_TIME_END_MONTH = "end_month";// ������
	public static String DYNAMIC_TIME_END_DAY = "end_day";// ��������
	public static String DYNAMIC_TIME_END_HOUR = "end_hour";// ����Сʱ
	public static String DYNAMIC_TIME_END_MINUTE = "end_minute";// ��������
	public static String DYNAMIC_TIME_END_SECOND = "end_second";// ������
	public static String DYNAMIC_TIME_END_WEEK = "end_week";// ������
	public static String DYNAMIC_TIME_END_WEEK_DAY = "end_week_day";// ����������

	public static String DYNAMIC_DISTANT = "distant";// �൱ǰʱ���
	public static String DYNAMIC_DISTANT_UNIT = "distantunit";// �൱ǰʱ�䵥λ
	public static String DYNAMIC_LEN = "len";// ���ʱ��ʱ�Ŀ��
	public static String DYNAMIC_LEN_UNIT = "len_unit";// ���ʱ��Ŀ�ȵ�λ

	public static String DYNAMIC_MODE = "mode";// ����ֵͳ�Ʒ�ʽ,������ʷ�����ӿ�
	public static final int DYNAMIC_MODE_MAX = 1;// ���ֵ
	public static final int DYNAMIC_MODE_MIN = 2;// ��Сֵ
	public static final int DYNAMIC_MODE_AVE = 3;// ƽ��ֵ
	public static final int DYNAMIC_MODE_BEGIN = 4;// ��ʼֵ
	public static final int DYNAMIC_MODE_END = 5;// ��ֵֹ

	public static String DYNAMIC_PERIOD = "period";// ��������
	public static String DYNAMIC_QUERY_UNIT = "query_unit";// �������ڵ�λ

	public static final int DYNAMIC_DIRECTION_HORIZONTIAL = 0;// ����
	public static final int DYNAMIC_DIRECTION_VERTICAL = 1;// ����
	public static final String DYNAMIC_X_DIRECTION = "x_direction"; // X�������������
	public static final String DYNAMIC_Y_DIRECTION = "y_direction"; // Y�������������
	public static final String DYNAMIC_DECIMAL= "decimal"; // Y�������������
	public static final String DYNAMIC_Y_TYPE= "y_type"; //����������

	public static final int DYNAMIC_X_PACE_1 = 1; // ÿ���㶼ȡ
	public static final int DYNAMIC_X_PACE_2 = 2; // ��1����ȡ
	public static final int DYNAMIC_X_PACE_3 = 3; // ��2����ȡ
	public static String DYNAMIC_X_PACE = "x_pace"; // X�������ʽ
	public static String DYNAMIC_Y_PACE = "y_pace"; // Y�������ʽ

	public static String DYNAMIC_POINT = "point";// ��������
	public static String DYNAMIC_POINT_MODE = "pointmode";// ����Դ��ʽ
	public static final int DYNAMIC_POINT_MODE_0 = 0;// ��Ⱦ�
	public static final int DYNAMIC_POINT_MODE_1 = 1;// ��Ȼ�Ⱦ�
	public static final int DYNAMIC_POINT_MODE_2 = 2;// ��Ⱦࣨʱ��Tip��

	/** NARI ʱ���ʽ���� */
	// ��
	public static final int THIS_SECOND = -1;
	public static final int LAST_SECOND = -2;
	public static final int LAST_TWO_SECOND = -3;
	public static final int NEXT_ONE_SECOND = 60;
	public static final int NEXT_TWO_SECOND = 61;
	// ����
	public static final int THIS_MINUTE = -1;// ����
	public static final int LAST_MINUTE = -2; // ǰһ��
	public static final int LAST_TWO_MINUTE = -3;// ǰ����
	public static final int LAST_FIVE_MINUTE = -6; // ǰ���
	public static final int NEXT_ONE_MINUTE = 60;// ��һ��
	public static final int NEXT_TWO_MINUTE = 61;// ������
	public static final int NEXT_FIVE_MINUTE = 64; // �������
	// Сʱ
	public static final int THIS_HOUR = -1;
	public static final int LAST_HOUR = -2;
	public static final int NEXT_ONE_HOUR = 24;
	public static final int NEXT_TWO_HOUR = 25;
	// ��
	public static final int THIS_DAY = 0;
	public static final int LAST_DAY = -1;
	public static final int LAST_TWO_DAY = -2;
	public static final int LAST_THREE_DAY = -3;
	public static final int NEXT_DAY = 32;// ����
	public static final int NEXT_TWO_DAY = 33;// ����
	// ��
	public static final int THIS_MONTH = 0;
	public static final int LAST_MONTH = -1;
	public static final int LAST_TWO_MONTH = -2;
	public static final int LAST_THREE_MONTH = -3;
	public static final int NEXT_ONE_MONTH = 13; // ����
	public static final int NEXT_TWO_MONTH = 14;// ������
	// ��
	public static final int THIS_YEAR = 0; // ����
	public static final int LAST_YEAR = -1; // ��һ��
	public static final int LAST_TWO_YEAR = -2; // ������
	public static final int LAST_THREE_YEAR = -3; // ������
	public static final int LAST_FOUR_YEAR = -4; // ������
	public static final int NEXT_ONE_YEAR = 1; // ��һ��
	public static final int NEXT_TWO_YEAR = 2; // �¶���
	// ��
	public static final int THIS_WEEK = 0;// ����
	public static final int MON = 1;
	public static final int TUES = 2;
	public static final int WED = 3;
	public static final int TURS = 4;
	public static final int FRIDAY = 5;
	public static final int SAT = 6;
	public static final int SUNDAY = 0;
	public static final int THIS_WEEKDAY = 10;// ����
	public static final int LAST_WEEKDAY = -1;// ����
	public static final int LAST_TWO_WEEKDAY = -2;// ǰ��
	public static final int NEXT_ONE_WEEKDAY = 7;// ����
	public static final int NEXT_TWO_WEEKDAY = 8;// ����

	public static String LINE_STYLE_NULL_STR = "��";  //  @jve:decl-index=0:
	public static String LINE_STYLE_SOLID_STR = "ʵ��";  //  @jve:decl-index=0:
	public static String LINE_STYLE_DASH_STR = "����";  //  @jve:decl-index=0:
	public static String LINE_STYLE_DOT_STR = "����";  //  @jve:decl-index=0:
	public static String LINE_STYLE_DASH_DOT_STR = "�㻮��-.-";  //  @jve:decl-index=0:
	public static String LINE_STYLE_DASH_DOT_DOT_STR = "�㻮��-..-";  //  @jve:decl-index=0:
	
	public static void main(String[] args) {
		
	}
}
