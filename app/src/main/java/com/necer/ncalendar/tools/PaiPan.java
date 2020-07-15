package com.necer.ncalendar.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.util.Log;

import com.necer.utils.SiZhuData;

public class PaiPan {

    //建 除 满 平 定 执 破 危 成 收 开 闭

    private int DAYUN_COUNT = 8;// 需要的大运的数目
    private Calendar calendar;
    // ==========干支数组===================

    final String[] Gan = new String[]{"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
    public static final String[] Zhi = new String[]{"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};
    final String[] JianXingZhi = new String[]{"寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥", "子", "丑"};
    public static final String[] JianXing = new String[]{"建", "除", "满", "平", "定", "执", "破", "危", "成", "收", "开", "闭"};
    public static final String[] JianXingNew = new String[]{"开", "闭", "建", "除", "满", "平", "定", "执", "破", "危", "成", "收"};
    public static final String[] JianXingNew2 = new String[]{"收", "开", "闭", "建", "除", "满", "平", "定", "执", "破", "危", "成"};
    public static final String[] JianXingNew3 = new String[]{"成", "收", "开", "闭", "建", "除", "满", "平", "定", "执", "破", "危"};
    public static final String[] JianXingNew4 = new String[]{"危", "成", "收", "开", "闭", "建", "除", "满", "平", "定", "执", "破"};
    public static final String[] JianXingNew5 = new String[]{"破", "危", "成", "收", "开", "闭", "建", "除", "满", "平", "定", "执"};
    public static final String[] JianXingNew6 = new String[]{"执", "破", "危", "成", "收", "开", "闭", "建", "除", "满", "平", "定"};
    public static final String[] JianXingNew7 = new String[]{"定", "执", "破", "危", "成", "收", "开", "闭", "建", "除", "满", "平"};
    public static final String[] JianXingNew8 = new String[]{"平", "定", "执", "破", "危", "成", "收", "开", "闭", "建", "除", "满"};
    public static final String[] JianXingNew9 = new String[]{"满", "平", "定", "执", "破", "危", "成", "收", "开", "闭", "建", "除"};
    public static final String[] JianXingNew10 = new String[]{"除", "满", "平", "定", "执", "破", "危", "成", "收", "开", "闭", "建"};
    public static final String[] JianXingNew11 = new String[]{"建", "除", "满", "平", "定", "执", "破", "危", "成", "收", "开", "闭"};
    public static final String[] JianXingNew12 = new String[]{"闭", "建", "除", "满", "平", "定", "执", "破", "危", "成", "收", "开"};

    //    final String[] zhagenYun = new String[]{"葵花树", "桂花树", "松树", "寺院", "靠城", "松林", "扎路", "绣楼厅", "河边上", "河套中"};
    final static Map<String, String> zhagenYun = new HashMap<String, String>() {{
        put("甲", "葵花树");
        put("乙", "桂花树");
        put("丙", "松树");
        put("丁", "寺院");
        put("戊", "靠城");
        put("己", "松林");
        put("庚", "扎路");
        put("辛", "绣楼厅");
        put("壬", "河边上");
        put("癸", "河套中");
    }};
    final String[] NaYin = new String[]{"海中金", "海中金", "炉中火", "炉中火", "大林木", "大林木", "路旁土", "路旁土", "剑锋金", "剑锋金", "山头火",
            "山头火", "涧下水", "涧下水", "城头土", "城头土", "白腊金", "白腊金", "杨柳木", "杨柳木", "泉中水", "泉中水", "屋上土", "屋上土", "霹雳火", "霹雳火",
            "松柏木", "松柏木", "长流水", "长流水", "沙中金", "沙中金", "山下火", "山下火", "平地木", "平地木", "壁上土", "壁上土", "金箔金", "金箔金", "佛灯火",
            "佛灯火", "天河水", "天河水", "大驿土", "大驿土", "钗钏金", "钗钏金", "桑松木", "桑松木", "大溪水", "大溪水", "沙中土", "沙中土", "天上火", "天上火",
            "石榴木", "石榴木", "大海水", "大海水"};
    public final String[][] nayinArray = {
            //干支，纳音，岁数，男，女
            {"丙戌", "屋上土", "1", "3", "3"},
            {"乙酉", "泉中水", "2", "4", "2"},
            {"甲申", "泉中水", "3", "5", "1"},
            {"癸未", "杨柳木", "4", "6", "9"},
            {"壬午", "杨柳木", "5", "7", "8"},
            {"辛巳", "白蜡金", "6", "8", "7"},
            {"庚辰", "白蜡金", "7", "9", "6"},
            {"己卯", "城墙土", "8", "1", "5"},
            {"戊寅", "城墙土", "9", "2", "4"},
            {"丁丑", "涧下水", "10", "3", "3"},
            {"丙子", "涧下水", "11", "4", "2"},
            {"乙亥", "山头火", "12", "5", "1"},
            {"甲戌", "山头火", "13", "6", "9"},
            {"癸酉", "剑锋金", "14", "7", "8"},
            {"壬申", "剑锋金", "15", "8", "7"},
            {"辛未", "路旁土", "16", "9", "6"},
            {"庚午", "路旁土", "17", "1", "5"},
            {"己巳", "大林木", "18", "2", "4"},
            {"戊辰", "大林木", "19", "3", "3"},
            {"丁卯", "炉中火", "20", "4", "2"},
            {"丙寅", "炉中火", "21", "5", "1"},
            {"乙丑", "海中金", "22", "6", "9"},
            {"甲子", "海中金", "23", "7", "8"},
            {"癸亥", "大海水", "24", "8", "7"},
            {"壬戌", "大海水", "25", "9", "6"},
            {"辛酉", "石榴木", "26", "1", "5"},
            {"庚申", "石榴木", "27", "2", "4"},
            {"己未", "天上火", "28", "3", "3"},
            {"戊午", "天上火", "29", "4", "2"},
            {"丁巳", "沙中土", "30", "5", "1"},
            {"丙辰", "沙中土", "31", "6", "9"},
            {"乙卯", "大溪水", "32", "7", "8"},
            {"甲寅", "大溪水", "33", "8", "7"},
            {"癸丑", "桑松木", "34", "9", "6"},
            {"壬子", "桑松木", "35", "1", "5"},
            {"辛亥", "钗钏金", "36", "2", "4"},
            {"庚戌", "钗钏金", "37", "3", "3"},
            {"己酉", "大驿土", "38", "4", "2"},
            {"戊申", "大驿土", "39", "5", "1"},
            {"丁未", "天河水", "40", "6", "9"},
            {"丙午", "天河水", "41", "7", "8"},
            {"乙巳", "佛灯火", "42", "8", "7"},
            {"甲辰", "佛灯火", "43", "9", "6"},
            {"癸卯", "金箔金", "44", "1", "5"},
            {"壬寅", "金箔金", "45", "2", "4"},
            {"辛丑", "壁上土", "46", "3", "3"},
            {"庚子", "壁上土", "47", "4", "2"},
            {"己亥", "平地木", "48", "5", "1"},
            {"戊戌", "平地木", "49", "6", "9"},
            {"丁酉", "山下火", "50", "7", "8"},
            {"丙申", "山下火", "51", "8", "7"},
            {"乙未", "沙中金", "52", "9", "6"},
            {"甲午", "沙中金", "53", "1", "5"},
            {"癸巳", "长流水", "54", "2", "4"},
            {"壬辰", "长流水", "55", "3", "3"},
            {"辛卯", "松柏木", "56", "4", "2"},
            {"庚寅", "松柏木", "57", "5", "1"},
            {"己丑", "霹雳火", "58", "6", "9"},
            {"戊子", "霹雳火", "59", "7", "8"},
            {"丁亥", "屋上土", "60", "8", "7"},};

    public final String[] otherNayin = new String[]{
            "甲子甲木子中癸水",
            "乙丑乙木丑中己土",
            "丙寅丙火寅中甲木",
            "丁卯丁火卯中乙木",
            "戊辰戊土辰中戊土",
            "己巳己土巳中丁火",
            "庚午庚金午中丁火",
            "辛未辛金未中己土",
            "壬申壬水申下庚金",
            "癸酉癸水酉下辛金",
            "甲戌甲木戌中午土",
            "乙亥乙木亥中癸水",
            "丙子丙火子中壬水",
            "丁丑丁火丑中己土",
            "午寅午土寅中甲木",
            "己卯己土卯中乙木",
            "庚辰庚金辰中午土",
            "辛巳辛金巳中丁火",
            "壬午壬水午中丙火",
            "癸未癸水未中己土",
            "甲申甲木申下庚金",
            "乙酉乙木酉中辛金",
            "丙戌丙火戌中午土",
            "丁亥丁火亥中癸水",
            "午子午土子中壬水",
            "己丑己土丑中己土",
            "庚寅庚金寅中甲木",
            "辛卯辛金卯中乙木",
            "壬辰壬水辰中午土",
            "癸巳癸水巳中丁水",
            "甲午甲木午中丙火",
            "乙未乙木未中己土",
            "丙申丙火申下庚金",
            "丁酉丁火酉中辛金",
            "戊戌午土戌中午土",
            "己亥己土亥中癸水",
            "庚子庚金子中壬水",
            "辛丑辛金丑中己土",
            "壬寅壬水寅中甲木",
            "癸卯癸水卯中乙木",
            "甲辰甲木辰中午土",
            "乙巳乙木巳中丁火",
            "丙午丙火午中丙火",
            "丁未丁火未中己土",
            "午申午土申下庚金",
            "己酉己土酉中辛金",
            "庚戌庚金戌中午土",
            "辛亥辛金亥中癸水",
            "壬子壬水子中壬水",
            "癸丑癸水丑中己土",
            "甲寅甲木寅中甲木",
            "乙卯乙木卯中乙木",
            "丙辰丙火辰中午土",
            "丁巳丁火巳中丁火",
            "午午午土午中丙火",
            "己未己土未中己土",
            "庚申庚金申下庚金",
            "辛酉辛金酉中辛金",
            "壬戌壬水戌中午土",
            "癸亥癸水亥中癸水",
    };

    public static final HashMap<String, String> LiuShiGua = new HashMap<String, String>() {
        {
            put("甲子甲木子中癸水", "资质良好，有进取心，理解力强，分析事情往往一语中的。脾气暴躁，性格外向，为人主观，难以接受别人的意见。内心极富道德感，崇尚和平，具有创造性，喜欢动脑，才智过人。新想法很多，但缺乏实际行动。在爱情方面，喜欢以实际行动来表达感情，有时给人不解风情的感觉，但实际上是个值得信赖的人。");
            put("乙丑乙木丑中己土", "性格直率，刚毅固执，容易得罪人或是被人利用。缺乏耐心，学的知识很多，但精通的很少，注意收敛自己的脾气。仁慈正直，随和，慷慨。能勇于面对困难，有见义勇为的精神。");
            put("丙寅丙火寅中甲木", "性格稳重，宽宏大量重义气，言出必行，做事踏实稳重。富有才华，独立性强，但多眼高手低，自制力差。易受他人欺骗，但不贪婪就不会因小失大。");
            put("丁卯丁火卯中乙木", "大多单纯可爱，待人热情，做事认真，心胸宽阔，有度量。性格直率，在情绪低落时会迁怒于人，要注意控制脾气。眼光独到，具有领导才能，能运用自身影响力来实现目标。");
            put("戊辰戊土辰中戊土", "有独裁倾向，但没有火龙那么明显。有爱心，不娇柔做作，心地善良，不自私，不贪财。为人慷慨，目光长远，富有上进心，做事深谋远虑。");
            put("己巳己土巳中丁火", "性情温和，天生聪明，反应很快，喜欢钻研，做事有计划，有创新精神。心胸宽阔，性格豪爽，感情强烈，有占有欲，喜欢控制别人，本身很受异性关注。");
            put("庚午庚金午中丁火", "感情丰富，脾气倔强，不认输，心地善良，与人相处坦诚。不喜欢勾心斗角，遇到朋友有困难会全力相助，有人员心直口快，会在不知不觉中得罪人，要注意说话的方式。");
            put("辛未辛金未中己土", "心地善良，志向远大，有责任心，讲信誉。对人对事比较冷漠，坚持自己的原则，有时很固执，在感情方面优柔寡断，感情发展缓慢，缺少决断性。");
            put("壬申壬水申下庚金", "具有领导才能，为人机灵，反应敏捷，但个性过于强硬，做事急躁轻率，常在不完全了解事情时就妄下断言，盛气凌人，易引起他人反感，甚至给自己的生活，工作带来阻碍。");
            put("癸酉癸水酉下辛金", "天生聪明，反应很快，随机应变的能力很强，擅长言辞，做事公正，乐于助人，人缘很好。心直口快，容易得罪人，要重视说话方式，避免产生是非。是非，心地善良，感情丰富，容易陷入感情漩涡。本身对异性很有吸引力，在赚钱方面有自己的独到之处。擅长持家。");
            put("甲戌甲木戌中午土", "天生善良，待人和气，做事踏实稳重，态度认真，有上进心，足智多谋，口齿伶俐，颇有领导者风范。思维缜密富有同情心乐于助人，但会衡量自己的能力。严谨，理性，能在感性和理性间找到一个合适的平衡点。善于计划，勤劳节俭，但过于斤斤计较会引起他人反感。");
            put("乙亥乙木亥中癸水", "聪明善良，喜欢结交朋友，为人随和，与人相处和睦，喜欢帮助别人，有时脾气比较急躁，但是意志力坚定。决定要做的事情就会着手进行，执行力强，不畏惧困难。崇尚自由人。为人大方。有时会有浪费的倾向，外表看起来十分温和，但实际内心固执，不喜欢妥协，充满活力，但平时沉默寡言，不善言辞。");
            put("丙子丙火子中壬水", "天性资质良好。温文儒雅，性格好动，充满活力，待人平和谦恭，做事干净利索，不拖泥带水。恩怨分明。性格自私，只为自己着想，忽视别人的想法和利益。");
            put("丁丑丁火丑中己土", "聪明伶俐，但个性急躁，性情不稳定，易受外界的诱惑和影响。心胸狭窄，目光短浅，自私，朋友很少，爱好自由，不喜欢被束缚，在婚姻方面，家庭不够稳定。");
            put("午寅午土寅中甲木", "为人善良，喜欢帮助他人，头脑聪明，自信心强，有野心，富有创业精神，工作态度认真。野心大，比较骄傲，也会因此产生贪欲，导致失败。具有苦干精神，擅长思考，能把内心的想法表达出来。天生热爱自由，不喜欢受约束，适合独自到异乡闯荡。家庭责任感薄弱，婚姻容易出现问题。");
            put("己卯己土卯中乙木", "个性率直，为人豪爽，但经常给人死板的印象。言行举止让人觉得没有礼貌，不懂情趣，在事业上很有野心，志向远大，具有创业精神。");
            put("庚辰庚金辰中午土", "个性直率，思想单纯，心地善良，待人和气，吃苦耐劳，经常提出建设性的意见，但情绪不稳定，经常改变主意。有犹豫不决的情况发生，缺乏恒心和毅力。");
            put("辛巳辛金巳中丁火", "天生聪明，足智多谋，擅长随机应变，具有自我保护意识，善良，喜欢帮助别人，无论是在金钱方面还是感情方面，有追逐权力的倾向，而且富有责任感。");
            put("壬午壬水午中丙火", "大多心地善良，性情温和，乐于助人，凡是能为他人着想，有舍己为人的精神，人缘很好。个性急躁，有时很情绪化，但会得到朋友的支持和帮助，有经商的天分，对经营和管理有独到的见解。异性缘不错，婚后生活幸福，但处理感情有些优柔寡断，家庭责任感不强，婚后夫妻俩要多沟通交流。");
            put("癸未癸水未中己土", "待人和气，真诚不喜欢出风头，做事细心认真，有责任感，讲义气，舍己为人，很少考虑个人得失。性格内向，思想独立，适合从事文艺或研究方面的工作，多愁善感，遇到困难容易产生逃避心理，特别是感情困扰。有艺术天分，联想和想象能力都很强，具有浪漫特质。");
            put("甲申甲木申下庚金", "天生乐观，做事勤奋，富有同情心，乐于助人。时间观念很强，有责任感，会全力以赴的投入工作，对一些事物的看法肤浅，不喜欢深入研究，没有长远眼光。");
            put("乙酉乙木酉中辛金", "天生聪明，头脑灵活，志向远大，做事有冲劲儿，不怕面对困难，会想办法克服困难。待人亲切，乐于助人，有时目标定得过高，不切实际，有时做事虎头蛇尾。依赖性强，缺乏独立奋斗的精神和自信，要尝试自力更生。感情丰富，有吸引异性的魅力。但热情难以持久。");
            put("丙戌丙火戌中午土", "个性豪爽，为人慷慨，讲义气。不看重金钱，不喜欢与人斤斤计较，有点儿内向，人际关系一般，有艺术天分，不喜欢勾心斗角，不宜从事竞争激烈的行业。");
            put("丁亥丁火亥中癸水", "性格善良，乐于助人，善于接受朋友意见，人缘很好，志向高远，适合自主创业，有时依赖性过重，擅长理财，不会浪费金钱，但有时给人吝啬的感觉。");
            put("午子午土子中壬水", "秉性聪颖，足智多谋，有独特思维能力，为人热情，善良，自尊心强，敢作敢当，富有冒险精神。个性稳重，为人精明，处事讲究务实，刚毅果断。");
            put("己丑己土丑中己土", "勤劳节俭，精打细算，但有时去斤斤计较，不会做超出自己能力范围的事。重视利益分配的公平性，为人忠厚老实，心直口快，不喜欢用心计。不欺瞒蒙骗，天资聪颖，胆识过人，做事刚毅果断，有责任感，敢作敢当，对未来充满自信，好面子，为达到目的不择手段。善良，富有正义感和责任感，颇能吸引异性顷心");
            put("庚寅庚金寅中甲木", "为人豪爽，心直口快，意志力坚强，忍耐性也超强，天生的领导才能。喜欢追逐权力，天分加努力必将在事业上取得较大的成就。凡是以自我为中心，不容易接受他人的劝告。猜忌心强，不容易相信他人，特别是感情方面儿的情感内敛，缺乏必要的沟通，多粗心大意，行事莽撞。");
            put("辛卯辛金卯中乙木", "严格要求自己，个性善良，不喜欢与人争斗，人际关系好。个性坚强，具有顽强的意志力，很少向困难低头，上进心很强，但情绪不稳定，易做出错误的判断。");
            put("壬辰壬水辰中午土", "工作积极主动，喜欢广交朋友，人际关系很好，志向远大，做事有毅力，但有时缺乏自主性。做事随大流，性格开朗大方，幽默风趣，愿意与人分享自己的成功。处事公正，但有时过于固执，还有点儿自私。涉及到自己利益的事情就会斤斤计较，甚至会因小失大，喜欢自由开放，但是不会出轨。婚姻比较稳定。");
            put("癸巳癸水巳中丁水", "天性活泼，反应敏捷，即使遇到突发情况也能很快解决。擅长言辞，具有艺术天分。懂得把握机会，适当经商。大多很聪明，对事物有敏锐的观察力，就具有冒险精神。擅长交际，注意仪表，很吸引意向眼眼光。很有异性缘儿。感情状态不错，对喜欢的人温柔体贴。");
            put("甲午甲木午中丙火", "勤劳朴实，但是个性急躁，容易冲动，做事虎头蛇尾，遇到挫折不能坚持，多半半途而废。主观意识十分强烈，喜欢自由，不喜欢受到约束和束缚。不擅长与人相处，经常得罪人，应重视人际关系。");
            put("乙未乙木未中己土", "性格开朗，心直口快，待人有礼貌，富有同情心，愿意帮助别人，很少专断独行，但是喜欢追求权利。喜欢独立思考，精力旺盛，有才华，做事小心谨慎，性格优柔寡断。做事经常瞻前顾后，容易错失良机，不喜欢被欺骗，嫉妒心强，不允许背叛行为。凡事喜欢亲力亲为，不喜欢别人帮忙团队协作能力欠佳");
            put("丙申丙火申下庚金", "欲望强烈执着，精神充沛，不畏惧竞争带来的压力，容易感情用事，外表看起来踏实稳重，其实性格十分急躁，观察片面，缺乏组织能力。对现实不满，好高骛远。");
            put("丁酉丁火酉中辛金", "个性独立重视自己的隐私不喜欢与别人过分亲近喜欢追求自由和刺激不喜欢循规蹈矩，平淡无味的生活。头脑聪明，具有领导能力，但不够自信，性格比较急躁。");
            put("戊戌午土戌中午土", "心胸宽阔重情义，重信义，善解人意，做事规矩讲原则不欺上瞒下，有责任感，全身心投入工作，会努力达成目标，知恩图报，不畏惧强权，敢作敢当，很容易得罪人。");
            put("己亥己土亥中癸水", "个性善良，喜欢结交朋友，遇到困难时有朋友主动帮忙。为人坦率，待人真诚，不欺上瞒下，工作踏实认真，乐观豁达，有时间观念，只要认真，就会成功。");
            put("庚子庚金子中壬水", "凡是积极，有行动力，是个完美的理想主义者，口齿伶俐，温文儒雅。只喜欢美的事物，生活品味极高，主观，固执，一旦认定的事情就会坚持到底。");
            put("辛丑辛金丑中己土", "生性好动，喜欢充实的生活，工作起来废寝忘食，有责任心。不怕吃苦，遇到困难会勇往直前，不逃避现实。感情丰富，性格温和待人，宫颈人缘好。过于重视友情，同情心丰富，易被别有用心的人利用。所以凡事要多听人劝告，才不容易上当。");
            put("壬寅壬水寅中甲木", "天生聪明，个性稳重，具有超强的应变能力和学习能力。对任何人和事都很平和，擅长社交，对新生事物有独特的见解，有设计天分。判断事物准确，如果从事研发工作多会成功。");
            put("癸卯癸水卯中乙木", "个性温和，适应环境能力强。不擅长表现自己，感情丰富，个性保守。为人处世是比较被动，有时还会得过且过。很少全力以赴的去完成一件事，喜欢随遇而安，容易获得满足，不擅长表达内心的感情，甚至对爱情的态度也不积极。一对信任的人产生依赖感，一旦失去依靠就会一直消沉。");
            put("甲辰甲木辰中午土", "大多性格内向，沉默寡言，不擅长交际。朋友不多，但是才华出众，具有很强的推理能力，有创造性和想象力，凡事喜欢刨根问底。工作能力很强，喜欢接受挑战，但是不喜欢出风头。也不擅长表现自己，亲情淡薄，遇到困难时亲戚很难帮上忙，全靠自力更生，天生具有吸引异性的能力，追求者众多。");
            put("乙巳乙木巳中丁火", "非常注意自己的言行举止，性格十分稳重，为人随和，重感情，人际关系良好，朋友很多，有些势力，喜欢结交有权有势的人，做事的时候很被动，很少积极主动去做一件事，理想远大，但过于空伐。遇到困难就会陷入苦闷中。");
            put("丙午丙火午中丙火", "天生聪明，活泼好动，具有敏锐的观察力，行事小心谨慎，个性倔强，主观意识强烈，无意接受他人意见。处事有时不够冷静，应多听取他人意见，集思广益。");
            put("丁未丁火未中己土", "性情耿直，喜欢干净，外表温和但内心很好胜，不服输，做事果断，有时鲁莽。叛逆心重，一旦发火，脾气很大，有时很任性，固执，有时会接纳别人的意见，肯帮助别人。");
            put("午申午土申下庚金", "天生乐观，做事不怕困难，多才多艺，独立性强，心胸宽广，不喜欢与人争执，但是有点儿骄傲。个人主观意识强烈，对他人有很强的戒心，也容易得罪人，遇事欠考虑，自视清高，刚复自用。不能专心于一件事。");
            put("己酉己土酉中辛金", "生性好动，喜欢思考，对哲学很有兴趣，喜欢探寻问题的本质，交友广泛，朋友很多，做事稳重踏实。头脑冷静，有恒心和动力，眼光独到，反应快，具有很强的判断力。");
            put("庚戌庚金戌中午土", "注意外表，爱慕虚荣，性格坦率，为人豪爽，外形俊美，乐于助人，勤奋细心，自尊心强，不喜欢依靠别人。具有不达目的不罢休的精神，个性保守，但人缘儿不错。");
            put("辛亥辛金亥中癸水", "性格开朗，待人和气，心胸豁达，讲信义，知恩图报，平和从容，不惹事生非，善于思考，有领导能力，但做事的态度不够积极。要树立恒心毅力。");
            put("壬子壬水子中壬水", "个性内敛，内心感情丰富，心地善良，富有同情心，积极助人，重感情，懂得知恩图报，对事物反应敏锐。为人洒脱，性情随和，人际关系良好，在需要帮助的时候，朋友都愿伸出援手，喜欢追求实际爱情，对爱情不抱幻想。喜欢口齿伶俐，博学多才的异性，需要伴侣时刻关心。");
            put("癸丑癸水丑中己土", "能吃苦耐劳，精明能干，喜欢钻研，乐善好施，人缘很好。爱好和平，富有正义感，具有领导才能，判断事情过于主观，不听从别人意见和建议，容易吃亏。");
            put("甲寅甲木寅中甲木", "现实，有私心，但又颇具正义感，经常会同情弱者。朋友有困难时候绝不会袖手旁观，也不会找借口推脱，擅长交际，但自以为是，很难听从别人意见。");
            put("乙卯乙木卯中乙木", "善良，老实，机智聪明，才华出众。做事谨慎小心，不喜欢尝试没做过的事情，为人踏实正直，容易相信别人，也因此经常被人欺骗，表面上给人活泼开朗的感觉，但内心害怕孤独，有心机，又有点自私，很少为他人着想，喜欢热闹，婚姻比较顺利，对感情也很执着。");
            put("丙辰丙火辰中午土", "天生有优越感，内秀，为人慷慨，乐于助人，性格开朗，喜欢结交朋友，异性缘和桃花运都很好，不是很聪明，但善于把握机会，做事容易获得成功。");
            put("丁巳丁火巳中丁火", "十分聪明，个性活泼。才华出众，重义气，朋友很多，性格直率，心直口快，喜欢不断变化的生活，做事积极，但缺乏耐心和毅力。");
            put("午午午土午中丙火", "性格温和，聪明伶俐，开朗豁达，一生运势不错，擅长交际，为人坦率，心地善良。喜欢为朋友排忧解难，性格急躁，但做事认真，有责任感。能够得到朋友的信赖");
            put("己未己土未中己土", "聪明，博学多才，但有时心直口快，说话不加考虑。不喜欢耍心机，乐于助人，在朋友中人缘较好，性格不稳定，做事易反复，面临重大选择时会犹豫不决。");
            put("庚申庚金申下庚金", "性格外向，聪明机智，语言幽默，多才多艺，自信心强，个性急躁，主观意识强烈，不接受他人意见。喜欢耍手段，玩心机，捉弄别人，容易引起别人反感。不擅长与人相处，人际关系一般。感情生活不顺利，不宜早婚。");
            put("辛酉辛金酉中辛金", "注重实际，为人乐观，反应敏捷，擅长表达自己的感情，分析能力强，做事踏实稳重，为人正直，待人公平，原则性强。有恒心和毅力，遇到困难和挫折不会退缩，会一直坚持到底。");
            put("壬戌壬水戌中午土", "性格开朗，活力十足，天生善良，讲信义，待人有礼貌，人缘好，喜欢思索，遇到困难时，总是逆来顺受，做事有长远的考虑，思想开放，但是行为很保守，做事态度不够积极。缺乏决断力，有时遇事犹豫不决，做决定的魄力不够，有时思考和行动步调不一致，配合的不够好，容易被感情束缚。能全心全意的投入，感情受困，难以自拔");
            put("癸亥癸水亥中癸水", "天生聪明，心灵手巧，擅长言辞，具有随机应变的能力，做事认真负责，但有时过于主观，有些固执，亲情淡薄，很多事情都要依靠自己的努力来完成。擅长社交，与朋友相处融洽，有商业头脑，经常沉溺于情欲中无法自拔，不能很好的区分性与情，言行举止粗鲁。容易引起别人反感，有钱后易陷入奢靡");
        }
    };

    // 计算干支的偏移值
    private int yearCyl, dayCyl, hourCyl;
    private String monthGanZhi = null;
    // 三种时间格式化格式
    static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH");
    static SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    static SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 构造函数
     *
     * @param cal
     */
    public PaiPan(Calendar cal) {
        calendar = cal;
        Date baseDate = null;
        try {
            baseDate = sdf1.parse("1900-1-31");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        yearCyl = getCheckedYear(cal) - 1864;

        // 求出月的干支
        monthGanZhi = getMonthGanZhiString(cal, getCheckedYear(cal));
        // 求出日和时的偏移值
        int offset = (int) ((cal.getTime().getTime() - baseDate.getTime()) / 86400000L);
        dayCyl = offset + 40;
        hourCyl = (int) ((cal.getTime().getTime() - baseDate.getTime() + 3300000L) / 7200000L);

    }


    public static String getNongLiMonth(Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");// 设置你想要的格式
            String dateStr = df.format(calendar.getTime());
            String nongLiMonth = NongLi.getDateMonth(dateStr);
            return nongLiMonth;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    /**
     * 输出四柱
     *
     * @return 四柱string
     */
    public String getSiZhuString() {// 返回四柱
        return cyclicalm(yearCyl) + monthGanZhi + cyclicalm(dayCyl) + cyclicalm(hourCyl);
    }

    /**
     * 获取4柱
     *
     * @return 四柱
     */
    public SiZhuData getSiZhuData() {
        SiZhuData siZhuData = new SiZhuData();
        siZhuData.setYear(calendar.get(Calendar.YEAR));
        siZhuData.setNianZhu(cyclicalm(yearCyl));
        siZhuData.setYueZhu(monthGanZhi);
        siZhuData.setRiZhu(cyclicalm(dayCyl));
        siZhuData.setShiZhu(cyclicalm(hourCyl));
        return siZhuData;
    }

    /**
     * 获取四柱对应的纳音
     *
     * @return 纳音
     */
    public String getNaYin(String sizhu) {
        for (int i = 0; i < nayinArray.length; i++) {
            if (nayinArray[i][0].equals(sizhu)) {
                return nayinArray[i][1];
            }
        }
        return "";
    }

    public String getOtherNayin(String sizhu) {
        for (int i = 0; i < otherNayin.length; i++) {
            if (otherNayin[i].contains(sizhu)) {
                return otherNayin[i];
            }
        }
        return "";
    }

    /**
     * 传入 月日的offset 传回干支, 0=甲子
     *
     * @param num
     * @return
     */
    final private String cyclicalm(int num) {
        return (Gan[num % 10] + Zhi[num % 12]);
    }

    /**
     * 输入日干支，返回空亡
     *
     * @param rigan
     * @return
     */
    public String getKWString(String rigan) {
        int gan = 0, zhi = 0, kong = 0;
        gan = getGanPosition(String.valueOf(rigan.charAt(0)));
        zhi = getZhiPosition(String.valueOf(rigan.charAt(1)));

        kong = (10 - gan - (12 - zhi) + 12) % 12;
        String kw = Zhi[kong] + Zhi[kong + 1];
        return kw;
    }

    /**
     * 输入性别和年干，返回排大运的方向
     *
     * @param gender  1、男 0、女
     * @param yeargan
     * @return
     */
    public int getPaiDaYunDir(int gender, String yeargan) {

        int dir = 1;
        int yg = getGanPosition(yeargan);
        if ((yg % 2 == 0 && gender == 1) || (yg % 2 == 1 && gender == 0)) {// 男阳/女阴
            dir = 1;
        } else if ((yg % 2 == 1 && gender == 1) || (yg % 2 == 0 && gender == 0)) {// 男阴/女阳
            dir = -1;
        }
        return dir;
    }

    /**
     * 排大运方法
     *
     * @param gender
     * @param yearganzhi
     * @param monthganzhi
     * @return
     */
    public String[] getDaYunString(int gender, String yearganzhi, String monthganzhi) {

        int dir = 1, monthgan = 0, i, monthzhi = 0;
        String[] daYun = new String[DAYUN_COUNT];

        dir = getPaiDaYunDir(gender, String.valueOf(yearganzhi.charAt(0)));

        monthgan = getGanPosition(String.valueOf(monthganzhi.charAt(0)));
        monthzhi = getZhiPosition(String.valueOf(monthganzhi.charAt(1)));
        // Log.e("tag", "transfer-->"+monthzhi);
        // Log.e("tag", "transfer-->"+monthganzhi.charAt(1));

        if (dir == 1) {
            for (i = 0; i < DAYUN_COUNT; i++) {
                daYun[i] = Gan[(++monthgan) % 10].toString() + (Zhi[(++monthzhi) % 12].toString());
            }
        } else {
            for (i = 0; i < DAYUN_COUNT; i++) {
                daYun[i] = Gan[((--monthgan) + 10) % 10].toString() + (Zhi[((--monthzhi) + 12) % 12].toString());
                // Log.e("tag", "dayundizhi-->"+(Zhi[((monthzhi) + 12) %
                // 12].toString()));
            }
        }

        return daYun;
    }

    /**
     * 返回输入的地支所对应的位置
     *
     * @param gan
     * @return
     */
    public int getGanPosition(String gan) {// 返回输入的天干所对应的位置

        int ganBack = 0, i = 0;
        for (i = 0; i < 10; i++) {
            if (Gan[i].toString().equals(gan)) {
                ganBack = i;
                break;
            } else {
                continue;
            }
        }
        return ganBack;
    }

    /**
     * 返回输入的地支所对应的位置
     *
     * @param zhi
     * @return
     */
    public int getZhiPosition(String zhi) {

        int zhiBack = 0, i = 0;
        for (i = 0; i < 12; i++) {
            if (Zhi[i].toString().equals(zhi)) {
                zhiBack = i;
                break;
            } else {
                continue;
            }
        }
        return zhiBack;
    }

    /**
     * 输入日期，返回月干支
     *
     * @param cal
     * @param year
     * @return
     */
    public String getMonthGanZhiString(Calendar cal, int year) {

        String monthGan = "丙戊庚壬甲";
        String monBeginGan = String.valueOf(monthGan.charAt((year - 1864) % 5));

        int gan = getGanPosition(monBeginGan);
        int zhi = getZhiPosition("寅");

        String[] monthDate = getWholeYearJieQis(year);

        // 创建当年的所有月的干支字符数组monthGanZhis
        String[] monthGanZhis = new String[12];
        for (int i = 0; i < 12; i++) {
            monthGanZhis[i] = Gan[(gan++) % 10] + Zhi[(zhi++) % 12];
        }

        return monthGanZhis[getPostionOfTheYear(cal, monthDate)];

    }

    /**
     * 输入校对后的年份，返回当年的所有节气的日期（立春~大寒）
     *
     * @param year
     * @return
     */
    public String[] getWholeYearJieQis(int year) {

        SolarTerm solarTerm = new SolarTerm();
        String[] nowYear = solarTerm.getLunarJieQisDateOfTheYear(year);
        String[] nextYear = solarTerm.getLunarJieQisDateOfTheYear(year + 1);

        String[] monthDate = new String[12];
        for (int i = 2; i < 12; i++) {
            monthDate[i - 2] = nowYear[i];
        }
        monthDate[10] = nextYear[0];
        monthDate[11] = nextYear[1];

        return monthDate;
    }

    /**
     * 返回立春日历时间
     *
     * @param year
     * @return
     */
    public Calendar getLiChunCalendar(int year) {
        SolarTerm stLiChun = new SolarTerm();
        Calendar calLiChun = Calendar.getInstance();
        try {
            calLiChun.setTime(sdf3.parse(stLiChun.getLiChunString(year)));// 找出当年立春时间

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calLiChun;
    }

    /**
     * 输入输入日期和当年节气的数组，返回所在节气的位置
     *
     * @param calInput
     * @param jqStrings
     * @return
     */
    public int getPostionOfTheYear(Calendar calInput, String[] jqStrings) {

        int outPut = 0, i = 0;
        String timeString = null;
        Calendar calTime = Calendar.getInstance();// 一个一个取出节气数组中的日期进行比较
        long inputTime = calInput.getTimeInMillis();
        long jqTime = 0;// 一个一个取出节气数组中的日期变为长整形数据进行比较

        for (i = 0; i < jqStrings.length; i++) {

            timeString = jqStrings[i];
            try {
                calTime.setTime(sdf4.parse(timeString));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            jqTime = calTime.getTimeInMillis();

            if (jqTime < inputTime) {
                continue;
            } else {
                break;
            }
        }
        outPut = i - 1;

        return outPut;

    }

    /**
     * 输入日期，返回校正后的年份
     *
     * @param cal
     * @return
     */
    final public int getCheckedYear(Calendar cal) {

        Calendar calLiChun = getLiChunCalendar(cal.get(Calendar.YEAR));
        return (cal.getTimeInMillis() > calLiChun.getTimeInMillis()) ? calLiChun.get(Calendar.YEAR)
                : (calLiChun.get(Calendar.YEAR) - 1);
    }

    /**
     * 输入日期返回相应的纳音
     *
     * @param cal
     * @return
     */
    final public String getNaYinString(Calendar cal) {

        Calendar calLiChun = getLiChunCalendar(cal.get(Calendar.YEAR));
        int year = (cal.getTimeInMillis() > calLiChun.getTimeInMillis()) ? calLiChun.get(Calendar.YEAR)
                : (calLiChun.get(Calendar.YEAR) - 1);

        return NaYin[(year - 1864) % 60];
    }

    // //=============================TestArea=========================================//
    // public static void main(String[] arg) {
    //
    // String birthday = 2014 + "-" + 12 + "-" + 12 + " " + 12;
    // dataCalc.SolarTerm stLiChun = new SolarTerm();
    //
    // Calendar calInput = Calendar.getInstance();
    // try {
    // calInput.setTime(sdf2.parse(birthday));
    // } catch (ParseException e) {
    // e.printStackTrace();
    // }
    //
    // PaiPan pp=new PaiPan(calInput);
    //
    // SolarTerm solarTerm=new SolarTerm();
    // String [] test=solarTerm.getLunarMonthsDateOfTheYear(2014);
    // for(int j=0;j<test.length;j++){
    // System.out.println("Here:"+test[j]);
    // }
    // }

    /**
     * 根据日柱获取扎根运类型
     *
     * @param riZhu 日柱
     * @return 扎根运类型
     */
    public static String getZhaGenYunFromRiZhu(String riZhu) {
        String real = riZhu.substring(0, 1);
        return zhagenYun.get(real);
    }

    /**
     * 获取扎根运第几阶段
     *
     * @param daYunYear 大运年龄
     * @return 扎根运第几段
     */
    public static int getZhaGenYunLevel(int daYunYear) {
        if (daYunYear <= 2) {
            return 1;
        } else if (daYunYear <= 4) {
            return 2;
        } else if (daYunYear <= 6) {
            return 3;
        } else if (daYunYear <= 8) {
            return 4;
        } else if (daYunYear <= 10) {
            return 5;
        }
        return 0;
    }

}
