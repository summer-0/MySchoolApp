package com.example.a49944.myapp.bean.schedule;

import java.util.List;

/**
 * Created by 49944
 * Time: 2019/5/10 21:31
 * Des:
 */
public class ScheduleWeekBean {

    /**
     * total : 5
     * rows : [{"dgksdm":"1415373","jxbmc":"150805,150806","pkrs":"84","kcmc":"网络协议分析","teaxms":"刘耀宗","xq":"1","jcdm":"0102","jxcdmc":"黄浩川教学楼104","zc":"2","kxh":"3","jxhjmc":"讲课","flfzmc":"","sknrjj":"第三讲_字节序与传输层协议","pkrq":"2018-03-12","rownum_":"1"},{"dgksdm":"1406308","jxbmc":"151005,150806,150804","pkrs":"144","kcmc":"毛泽东思想和中国特色社会主义理论体系概论(2)","teaxms":"黄文保","xq":"1","jcdm":"0910","jxcdmc":"北主楼1005","zc":"2","kxh":"3","jxhjmc":"讲课","flfzmc":"","sknrjj":"第三节扩大对外开放","pkrq":"2018-03-12","rownum_":"2"},{"dgksdm":"1415374","jxbmc":"150805,150806","pkrs":"84","kcmc":"网络协议分析","teaxms":"刘耀宗","xq":"3","jcdm":"0102","jxcdmc":"黄浩川教学楼104","zc":"2","kxh":"4","jxhjmc":"讲课","flfzmc":"","sknrjj":"第四讲_简单TCP程序设计","pkrq":"2018-03-14","rownum_":"3"},{"dgksdm":"1406309","jxbmc":"151005,150806,150804","pkrs":"144","kcmc":"毛泽东思想和中国特色社会主义理论体系概论(2)","teaxms":"黄文保","xq":"3","jcdm":"0708","jxcdmc":"北主楼1002","zc":"2","kxh":"4","jxhjmc":"讲课","flfzmc":"","sknrjj":"第八章建设中国特色社会主义总布局第一节建设中国特色社会主义经济","pkrq":"2018-03-14","rownum_":"4"},{"dgksdm":"1425960","jxbmc":"拆分班级2:150806[46]","pkrs":"46","kcmc":"网络协议分析","teaxms":"刘耀宗","xq":"3","jcdm":"1112","jxcdmc":"黎耀球楼202B","zc":"2","kxh":"17","jxhjmc":"实验","flfzmc":"","sknrjj":"实验一 ARP协议分析实验","pkrq":"2018-03-14","rownum_":"5"}]
     */

    private int total;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * dgksdm : 1415373
         * jxbmc : 150805,150806
         * pkrs : 84
         * kcmc : 网络协议分析
         * teaxms : 刘耀宗
         * xq : 1
         * jcdm : 0102
         * jxcdmc : 黄浩川教学楼104
         * zc : 2
         * kxh : 3
         * jxhjmc : 讲课
         * flfzmc :
         * sknrjj : 第三讲_字节序与传输层协议
         * pkrq : 2018-03-12
         * rownum_ : 1
         */

        private String dgksdm;
        private String jxbmc;
        private String pkrs;
        private String kcmc;
        private String teaxms;
        private String xq;
        private String jcdm;
        private String jxcdmc;
        private String zc;
        private String kxh;
        private String jxhjmc;
        private String flfzmc;
        private String sknrjj;
        private String pkrq;
        private String rownum_;

        public String getDgksdm() {
            return dgksdm;
        }

        public void setDgksdm(String dgksdm) {
            this.dgksdm = dgksdm;
        }

        public String getJxbmc() {
            return jxbmc;
        }

        public void setJxbmc(String jxbmc) {
            this.jxbmc = jxbmc;
        }

        public String getPkrs() {
            return pkrs;
        }

        public void setPkrs(String pkrs) {
            this.pkrs = pkrs;
        }

        public String getKcmc() {
            return kcmc;
        }

        public void setKcmc(String kcmc) {
            this.kcmc = kcmc;
        }

        public String getTeaxms() {
            return teaxms;
        }

        public void setTeaxms(String teaxms) {
            this.teaxms = teaxms;
        }

        public String getXq() {
            return xq;
        }

        public void setXq(String xq) {
            this.xq = xq;
        }

        public String getJcdm() {
            return jcdm;
        }

        public void setJcdm(String jcdm) {
            this.jcdm = jcdm;
        }

        public String getJxcdmc() {
            return jxcdmc;
        }

        public void setJxcdmc(String jxcdmc) {
            this.jxcdmc = jxcdmc;
        }

        public String getZc() {
            return zc;
        }

        public void setZc(String zc) {
            this.zc = zc;
        }

        public String getKxh() {
            return kxh;
        }

        public void setKxh(String kxh) {
            this.kxh = kxh;
        }

        public String getJxhjmc() {
            return jxhjmc;
        }

        public void setJxhjmc(String jxhjmc) {
            this.jxhjmc = jxhjmc;
        }

        public String getFlfzmc() {
            return flfzmc;
        }

        public void setFlfzmc(String flfzmc) {
            this.flfzmc = flfzmc;
        }

        public String getSknrjj() {
            return sknrjj;
        }

        public void setSknrjj(String sknrjj) {
            this.sknrjj = sknrjj;
        }

        public String getPkrq() {
            return pkrq;
        }

        public void setPkrq(String pkrq) {
            this.pkrq = pkrq;
        }

        public String getRownum_() {
            return rownum_;
        }

        public void setRownum_(String rownum_) {
            this.rownum_ = rownum_;
        }
    }
}
