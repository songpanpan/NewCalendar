# 安卓日历 NCalendar

## 特点:

 - 3种常见日历交互方式，MIUI系统日历：miui9、miui10、华为emui，miui9和钉钉日历类似，华为emui和365日历类似
 - 月周滑动切换，月周不选中
 - 支持多选，设置多选的数量
 - 支持设置默认视图，默认周日历或者月日历
 - 支持周状态固定，下拉刷新等
 - 支持设置一周开始的是周一还是周日
 - 可设置日期区间，默认区间从1901-01-01到2099-12-31 
 - 支持农历，节气、法定节假日等
 - 支持添加指示点及设置指示点位置
 - 支持各种颜色、距离、位置等属性
 - 支持日历和列表之间添加view
 - 支持替换农历、颜色等
 - 支持自定义日历页面
 - 支持内部TargetView为任意View
 - 支持日历拉伸功能

## 效果图 
|Miui9Calendar|Miui10Calendar|EmuiCalendar|
|:---:|:---:|:---:|
|![](https://github.com/yannecer/NCalendar/blob/master/app/miui9_gif.gif)|![](https://github.com/yannecer/NCalendar/blob/master/app/miui10_gif.gif)|![](https://github.com/yannecer/NCalendar/blob/master/app/emui_gif.gif)|

|周固定，下拉刷新|日历和子view添加其他view|自定义日历界面（LigaturePainter）|
|:---:|:---:|:---:|
|![](https://github.com/yannecer/NCalendar/blob/master/app/week_hold.gif)|![](https://github.com/yannecer/NCalendar/blob/master/app/addview.gif)|![](https://github.com/yannecer/NCalendar/blob/master/app/LigaturePainter.png)|

|默认不选中|默认多选|自定义日历界面（TicketPainter）|
|:---:|:---:|:---:|
|![](https://github.com/yannecer/NCalendar/blob/master/app/111.gif)|![](https://github.com/yannecer/NCalendar/blob/master/app/222.gif)|![](https://github.com/yannecer/NCalendar/blob/master/app/TicketPainter.png)|


|ViewPager|普通View|demo功能预览|
|:---:|:---:|:---:|
|![](https://github.com/yannecer/NCalendar/blob/master/app/viewpager.gif)|![](https://github.com/yannecer/NCalendar/blob/master/app/general.gif)|![](https://github.com/yannecer/NCalendar/blob/master/app/demo.png)|


|日历拉伸|
|:---:|
|![](https://github.com/yannecer/NCalendar/blob/master/app/Stretch.gif)|

# 下载demo：
[下载demo](https://github.com/yannecer/NCalendar/releases/download/4.3.0/4.3.0.apk)


## 使用方法

#### Gradle
```
implementation 'com.necer.ncalendar:ncalendar:4.3.8'

```

#### 月周切换

```
    miui9 和 钉钉日历
    <com.necer.calendar.Miui9Calendar
        android:id="@+id/miui9Calendar"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        >
        <android.support.v7.widget.RecyclerView
            android:id="@+id/recyclerView"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />
    </com.necer.calendar.Miui9Calendar>
    
    Miui10Calendar EmuiCalendar 用法类似
    
```

#### 单个月日历，单个周日历


```
   月日历
   <com.necer.calendar.MonthCalendar
        android:layout_width="match_parent"
        app:todaySolarTextColor="#ff00ff"
        app:selectCircleColor="#00c3aa"
        app:hollowCircleColor="#00c3aa"
        android:layout_height="300dp" />

   周日历
   <com.necer.calendar.WeekCalendar
        android:layout_width="match_parent"
        app:lunarTextColor="#00aa00"
        android:layout_height="50dp" />

```


#### 注意


- NCalendar（Miui9Calendar、Miui10Calendar、EmuiCalendar）内部```TargetView```可以是除```ScrollView```、```ListView```、```GridView```之外的任意```View```，处理滑动的内容请使用```RecyclerView```和```NestedScrollView```等实现了```NestedScrollingChild```的```View```
- 如果布局文件中，内部实际滑动的```TargetView```有多个父```View```，恰好也有实现了```NestedScrollingChild```的父```View```，则需要给实际滑动的子```View```设置```tag（“@string/factual_scroll_view”）```，不然可能会出现滑动异常，此种情况在下拉刷新中比较常见



### 交流群

技术交流QQ群：127278900 (请先仔细看文档，然后再进群发问，上方加粗字体有下载demo链接)
### 主要Api

#### 月日历、周日历、折叠月周日历共同拥有的api


```

   /**
     * 设置选中模式
     *
     * @param selectedMode SINGLE_SELECTED--日历默认选中模式  每个页面都有选中，左右页面为加减一月或者一周后的日期 
     *                     SINGLE_UNSELECTED--单个选中，不点击选中日期不会改变，翻页不改变选中日期
     *                     MULTIPLE--多选，多个选中，累计选中
     */
    void setSelectedMode(SelectedModel selectedMode);

    /**
     * 多选个数和模式
     *
     * @param multipleNum      多选限制的个数
     * @param multipleNumModel FULL_CLEAR--超过清除所有
     *                         FULL_REMOVE_FIRST--超过清除第一个
     */
    void setMultipleNum(int multipleNum, MultipleNumModel multipleNumModel);


    //默认选中时，是否翻页选中第一个，只在selectedMode==SINGLE_SELECTED有效
    void setDefaultSelectFitst(boolean isDefaultSelectFitst);

    //跳转日期
    void jumpDate(String formatDate);

    //上一页 上一周 上一月
    void toLastPager();

    //下一页 下一周 下一月
    void toNextPager();

    //回到今天
    void toToday();

    //设置自定义绘制类
    void setCalendarPainter(CalendarPainter calendarPainter);

    //刷新日历
    void notifyCalendar();

    //设置初始化日期
    void setInitializeDate(String formatInitializeDate);

    //设置初始化日期和可用区间
    void setDateInterval(String startFormatDate, String endFormatDate, String formatInitializeDate);

    //设置可用区间
    void setDateInterval(String startFormatDate, String endFormatDate);

    //单选日期变化监听
    void setOnCalendarChangedListener(OnCalendarChangedListener onCalendarChangedListener);

    //多选日期变化监听
    void setOnCalendarMultipleChangedListener(OnCalendarMultipleChangedListener onCalendarMultipleChangedListener);

    //设置点击了不可用日期监听
    void setOnClickDisableDateListener(OnClickDisableDateListener onClickDisableDateListener);

    //获取绘制类
    CalendarPainter getCalendarPainter();

    //获取全部选中的日期集合
    List<LocalDate> getAllSelectDateList();

    //获取当前页面选中的日期集合
    List<LocalDate> getCurrectSelectDateList();
    
    //获取当前页面的数据 如果是月周折叠日历 周状态下获取的是一周的数据，月状态下获取的一月的数据
    List<LocalDate> getCurrectDateList();
```

#### 折叠月周日历miui9，miui10，emui 拥有的api
```

     //回到周状态 只能从月->周
    void toWeek();

    //回到月状态 可以从周回到月或者从拉伸回到周
    void toMonth();

    //回到拉伸状态 只能从月->拉伸
    void toStretch();

    //设置周滑动到周位置固定
    void setWeekHoldEnable(boolean isWeekHoldEnable);

    //设置月状态下 下拉拉伸
    void setMonthStretchEnable(boolean isMonthStretchEnable);

    //日历月周状态变化回调
    void setOnCalendarStateChangedListener(OnCalendarStateChangedListener onCalendarStateChangedListener);

    //日历 月 周 拉伸 状态滑动监听
    void setOnCalendarScrollingListener(OnCalendarScrollingListener onCalendarScrollingListener);

    //设置日历状态
    void setCalendarState(CalendarState calendarState);

    //获取当前日历的状态  CalendarState.MONTH==月视图     CalendarState.WEEK==周视图  CalendarState.MONTH_STRETCH==日历拉伸状态
    CalendarState getCalendarState();

```


#### 添加指示圆点
```
此功能为默认 CalendarPainter 类 InnerPainter 的功能，如果设置了自定义 CalendarPainter ，没有此方法，需要自己实现

List<String> pointList = Arrays.asList("2018-10-01", "2018-11-19", "2018-11-20", "2018-05-23", "2019-01-01");
InnerPainter innerPainter = (InnerPainter) miui10Calendar.getCalendarPainter();
innerPainter.setPointList(pointList);

```
#### 设置法定节假日
```
此功能为默认 CalendarPainter 类 InnerPainter 的功能，如果设置了自定义 CalendarPainter ，没有此方法，需要自己实现

List<String> holidayList = Arrays.asList("2018-10-01", "2018-11-19", "2018-11-20");
List<String> holidayList = Arrays.asList("2019-10-01", "2019-11-19", "2019-11-20");

InnerPainter innerPainter = (InnerPainter) miui10Calendar.getCalendarPainter();
innerPainter.setLegalHolidayList(holidayList,workdayList);

```
#### 替换农历文字及颜色
```
此功能为默认 CalendarPainter 类 InnerPainter 的功能，如果设置了自定义 CalendarPainter ，没有此方法，需要自己实现

InnerPainter innerPainter = (InnerPainter) miui10Calendar.getCalendarPainter();

Map<String, String> strMap = new HashMap<>();
strMap.put("2019-01-25", "测试");
strMap.put("2019-01-23", "测试1");
strMap.put("2019-01-24", "测试2");
innerPainter.setReplaceLunarStrMap(strMap);

Map<String, Integer> colorMap = new HashMap<>();
colorMap.put("2019-01-25", Color.RED);
colorMap.put("2019-01-23", Color.GREEN);
colorMap.put("2019-01-24", Color.parseColor("#000000"));
innerPainter.setReplaceLunarColorMap(colorMap);
```
#### 添加日历拉伸之后的文字显示
```
日历支持拉伸之后显示一行文字 
此功能为默认 CalendarPainter 类 InnerPainter 的功能，如果设置了自定义 CalendarPainter ，没有此方法，需要自己实现

InnerPainter innerPainter = (InnerPainter) miui10Calendar.getCalendarPainter();
innerPainter.setPointList(pointList);

Map<String, String> strMap = new HashMap<>();
strMap.put("2019-07-01", "测试");
strMap.put("2019-07-19", "测试1");
strMap.put("2019-07-25", "测试2");
innerPainter.setStretchStrMap(strMap);
```



### CalendarPainter


```
日历绘制接口，绘制的所有内容通过这个接口完成，实现这个类可实现自定义的日历界面，
参数中的 rectF 是文字位置的矩形对象
日历内部内置了一个 InnerPainter ，各个属性也是这个绘制类的，如果自定义 CalendarPainter ，则这些属性都不适用
InnerPainter 实现了设置圆点、替换农历等方法，还可以实现更多方法，如多选，多标记等，


    //绘制月日历或这日历背景，如数字背景等 
    void onDrawCalendarBackground(CalendarView calendarView, Canvas canvas, RectF rectF, LocalDate localDate, int totalDistance, int currentDistance);

    //绘制今天的日期
    void onDrawToday(Canvas canvas, RectF rectF, LocalDate localDate, List<LocalDate> selectedDateList);

    //绘制当前月或周的日期
    void onDrawCurrentMonthOrWeek(Canvas canvas, RectF rectF, LocalDate localDate, List<LocalDate> selectedDateList);

    //绘制上一月，下一月的日期，周日历不用实现
    void onDrawLastOrNextMonth(Canvas canvas, RectF rectF, LocalDate localDate, List<LocalDate> selectedDateList);

    //绘制不可用的日期，和方法setDateInterval(startFormatDate, endFormatDate)对应 如果没有使用setDateInterval设置日期范围 此方法不用实现
    void onDrawDisableDate(Canvas canvas, RectF rectF, LocalDate localDate);



实现接口 CalendarPainter，分别重写以上几个方法，setCalendarPainter(calendarPainter)即可实现自定义日历界面

```

### CalendarDate
```
CalendarDate 日历中存放日期各种参数的类，包含公历、农历、节气、节日、属相、天干地支等

    public LocalDate localDate;//公历日期
    public Lunar lunar;//农历
    public String solarHoliday;//公历节日
    public String lunarHoliday;//农历节日
    public String solarTerm;//节气
   
其中Lunar为农历信息的对象


    public boolean isLeap;//是否闰年
    public int lunarDay;//农历天
    public int lunarMonth;//农历月
    public int lunarYear;//农历年
    public int leapMonth;//闰月

    public String lunarOnDrawStr;//农历位置需要绘制的文字
    public String lunarDayStr;//农历天 描述 廿二等
    public String lunarMonthStr;//农历月 描述
    public String lunarYearStr;//农历年 描述
    public String animals;//生肖
    public String chineseEra;//天干地支



CalendarDate对象通过 CalendarUtil 获取

CalendarDate calendarDate = CalendarUtil.getCalendarDate(LocalDate localDate);

```




## 感谢：

项目中日期计算使用  [joda-time](https://github.com/JodaOrg/joda-time)<br/>农历和节气数据是工具类，多谢



## 支持的属性：

|Attributes|forma|describe
|---|---|---|
|solarTextColor| color|公历日期的颜色
|lunarTextColor| color|农历日期的颜色
|solarHolidayTextColor| color|公历节假日的颜色
|lunarHolidayTextColor| color|农历节假日的颜色
|solarTermTextColor| color|节气颜色
|selectCircleColor| color|选中圈的颜色
|holidayColor|color| 法定节休息日颜色
|workdayColor|color| 法定节调休工作日颜色
|bgCalendarColor|color| 日历的背景
|bgChildColor|color| 日历包含子view的背景
|todaySelectContrastColor|color| 今天被选中是其他元素的对比色，比如 农历，圆点等
|pointColor| color |小圆点的颜色
|alphaColor| integer |不是本月的日期颜色的透明度0-255
|disabledAlphaColor| integer |日期区间之外的地日颜色的透明度0-255
|disabledString| string |点击日期区间之外的日期提示语
|todaySolarTextColor| color|今天不选中的颜色
|todaySolarSelectTextColor| color|今天选中的颜色
|selectCircleRadius| dimension | 选中圈的半径
|solarTextSize| dimension|公历日期字体大小
|lunarTextSize| dimension|农历日期字体大小
|lunarDistance| dimension|农历日期到公历字体的距离
|holidayTextSize| dimension|法定节假日字体的大小
|holidayDistance| dimension |法定节假日到公历的距离
|pointDistance| dimension |小圆点到公历的距离
|hollowCircleStroke| dimension |空心圆的宽度
|calendarHeight| dimension |日历的高度
|duration|integer| 日历自动滑动的时间
|isShowLunar| boolean |是否显示农历
|isShowHoliday|boolean| 是否显示法定节假日
|isDefaultSelect|boolean| 是否默认选中（只对单个月日历或者周日历有效）
|defaultCalendar|enum| 默认视图 week 或者 month
|pointLocation|enum| 指示点的文职 up（在公历的上方） 或者 down（在公历的下方） 默认是up
|firstDayOfWeek|enum| 一周开始的星期天还是星期一 sunday 或者 monday 默认是sunday
|holidayLocation|enum| 法定节假日相对公历日期的位置 top_right（右上方）、top_left（左上方）、bottom_right（右下方）、bottom_left（左下方）
|stretchTextSize|dimension| 拉伸之后显示字体的大小
|stretchTextColor|color| 拉伸之后显示字体的颜色
|stretchTextDistance|dimension| 拉伸显示的字体距离矩形中心的距离
|stretchCalendarHeight|dimension| 拉伸之后的高度
|isAllMonthSixLine|boolean| 是否每个月份都占用6行，默认为flase
|selectSolarTextColorColor|color| 选中公历颜色
|selectLunarTextColor|color| 选中农历颜色
|isShowNumberBackground|boolean| 是否显示数字背景
|numberBackgroundTextSize|dimension| 数字背景字体大小
|numberBackgroundTextColor|color| 数字背景字体颜色
|numberBackgroundAlphaColor|integer| 数字背景字体透明度0-255
|isLastNextMonthClickEnable|boolean| 月日历上下月是否可点击


## 更新日志
* 4.3.8<br/> 新增月日历上下月是否可点击的属性 isLastNextMonthClickEnable
* 4.3.7<br/> 修复选中月初月末，实际月份回调bug
* 4.3.6<br/> 增加数字背景以及渐变效果
* 4.3.4<br/> 修复周状态下滑动卡顿的bug
* 4.3.3<br/> 修复莫名跳转2099年的bug，增加是否每个月都是6行的属性
* 4.3.2<br/> 增加日历拉伸功能
* 4.2.0<br/> 支持任意非滑动的View，ViewPger等
* 4.1.2<br/> 完善LigaturePainter
* 4.1.1<br/> 修改选中模式为枚举，demo增加了两种自定义CalendarPainter
* 4.1.0<br/> 优化onDraw效率、修改CalendarPainter回调参数、新增多选日期数量
* 4.0.4<br/> 修复某些情况下选中回调返回null的bug
* 4.0.2<br/> 修复节气不显示的bug
* 4.0.1<br/> 1、新增月周切换日历多选<br/>2、新增默认不选中折叠<br/>3、新增翻页默认选中每月1号<br/>4、修复设置左右padding偏差



License
-------


     Copyright 2018 necer

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.


     password:spp2020
   
   

