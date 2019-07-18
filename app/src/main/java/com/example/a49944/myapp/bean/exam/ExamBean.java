package com.example.a49944.myapp.bean.exam;

import java.util.List;

/**
 * Created by 49944
 * Time: 2019/5/11 22:18
 * Des:
 */
public class ExamBean {

    /**
     * total : 5
     * rows : [{"ksrcdm":"100053295","xnxqdm":"201701","xs":"48","jkteaxms":"张先勇","ksrq":"2017-11-26","zc":"13","xq":"7","jcdm2":"05,06","kssj":"14:30--16:30","kslbmc":"正常考试","zwh":"","xqmc":"五邑大学","ksaplxmc":"学院考","xsbh":"3115004472","xsxm":"刘新华","kcbh":"0500610","kcmc":"数据库原理","sjbh":"700332","ksxs":"0","kscdmc":"综合实验大楼308","rownum_":"1"},{"ksrcdm":"100068294","xnxqdm":"201701","xs":"48","jkteaxms":"林秋贝","ksrq":"2018-01-02","zc":"18","xq":"2","jcdm2":"01,02","kssj":"08:00--09:30","kslbmc":"正常考试","zwh":"","xqmc":"五邑大学","ksaplxmc":"教务处统考","xsbh":"3115004472","xsxm":"刘新华","kcbh":"0700320","kcmc":"通信原理","sjbh":"700394","ksxs":"0","kscdmc":"北主楼305","rownum_":"2"},{"ksrcdm":"100042657","xnxqdm":"201701","xs":"48","jkteaxms":"容振邦,司徒伟俊","ksrq":"2018-01-09","zc":"19","xq":"2","jcdm2":"07,08","kssj":"16:40--18:10","kslbmc":"正常考试","zwh":"","xqmc":"五邑大学","ksaplxmc":"教务处统考","xsbh":"3115004472","xsxm":"刘新华","kcbh":"1400041","kcmc":"毛泽东思想和中国特色社会主义理论体系概论(1)","sjbh":"700302","ksxs":"0","kscdmc":"北主楼203","rownum_":"3"},{"ksrcdm":"100102582","xnxqdm":"201701","xs":"48","jkteaxms":"杭维颖","ksrq":"2018-01-15","zc":"20","xq":"1","jcdm2":"01,02","kssj":"08:00--10:00","kslbmc":"正常考试","zwh":"","xqmc":"五邑大学","ksaplxmc":"教务处统考","xsbh":"3115004472","xsxm":"刘新华","kcbh":"0800640","kcmc":"计算机网络原理","sjbh":"700556","ksxs":"0","kscdmc":"北主楼301","rownum_":"4"},{"ksrcdm":"100096432","xnxqdm":"201701","xs":"48","jkteaxms":"金旺春,李鹤喜","ksrq":"2018-01-18","zc":"20","xq":"4","jcdm2":"05,06","kssj":"14:30--16:30","kslbmc":"正常考试","zwh":"","xqmc":"五邑大学","ksaplxmc":"教务处统考","xsbh":"3115004472","xsxm":"刘新华","kcbh":"0800630","kcmc":"操作系统","sjbh":"700528","ksxs":"0","kscdmc":"北主楼204","rownum_":"5"}]
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
         * ksrcdm : 100053295
         * xnxqdm : 201701
         * xs : 48
         * jkteaxms : 张先勇
         * ksrq : 2017-11-26
         * zc : 13
         * xq : 7
         * jcdm2 : 05,06
         * kssj : 14:30--16:30
         * kslbmc : 正常考试
         * zwh :
         * xqmc : 五邑大学
         * ksaplxmc : 学院考
         * xsbh : 3115004472
         * xsxm : 刘新华
         * kcbh : 0500610
         * kcmc : 数据库原理
         * sjbh : 700332
         * ksxs : 0
         * kscdmc : 综合实验大楼308
         * rownum_ : 1
         */

        private String ksrcdm;
        private String xnxqdm;
        private String xs;
        private String jkteaxms;
        private String ksrq;
        private String zc;
        private String xq;
        private String jcdm2;
        private String kssj;
        private String kslbmc;
        private String zwh;
        private String xqmc;
        private String ksaplxmc;
        private String xsbh;
        private String xsxm;
        private String kcbh;
        private String kcmc;
        private String sjbh;
        private String ksxs;
        private String kscdmc;
        private String rownum_;

        public String getKsrcdm() {
            return ksrcdm;
        }

        public void setKsrcdm(String ksrcdm) {
            this.ksrcdm = ksrcdm;
        }

        public String getXnxqdm() {
            return xnxqdm;
        }

        public void setXnxqdm(String xnxqdm) {
            this.xnxqdm = xnxqdm;
        }

        public String getXs() {
            return xs;
        }

        public void setXs(String xs) {
            this.xs = xs;
        }

        public String getJkteaxms() {
            return jkteaxms;
        }

        public void setJkteaxms(String jkteaxms) {
            this.jkteaxms = jkteaxms;
        }

        public String getKsrq() {
            return ksrq;
        }

        public void setKsrq(String ksrq) {
            this.ksrq = ksrq;
        }

        public String getZc() {
            return zc;
        }

        public void setZc(String zc) {
            this.zc = zc;
        }

        public String getXq() {
            return xq;
        }

        public void setXq(String xq) {
            this.xq = xq;
        }

        public String getJcdm2() {
            return jcdm2;
        }

        public void setJcdm2(String jcdm2) {
            this.jcdm2 = jcdm2;
        }

        public String getKssj() {
            return kssj;
        }

        public void setKssj(String kssj) {
            this.kssj = kssj;
        }

        public String getKslbmc() {
            return kslbmc;
        }

        public void setKslbmc(String kslbmc) {
            this.kslbmc = kslbmc;
        }

        public String getZwh() {
            return zwh;
        }

        public void setZwh(String zwh) {
            this.zwh = zwh;
        }

        public String getXqmc() {
            return xqmc;
        }

        public void setXqmc(String xqmc) {
            this.xqmc = xqmc;
        }

        public String getKsaplxmc() {
            return ksaplxmc;
        }

        public void setKsaplxmc(String ksaplxmc) {
            this.ksaplxmc = ksaplxmc;
        }

        public String getXsbh() {
            return xsbh;
        }

        public void setXsbh(String xsbh) {
            this.xsbh = xsbh;
        }

        public String getXsxm() {
            return xsxm;
        }

        public void setXsxm(String xsxm) {
            this.xsxm = xsxm;
        }

        public String getKcbh() {
            return kcbh;
        }

        public void setKcbh(String kcbh) {
            this.kcbh = kcbh;
        }

        public String getKcmc() {
            return kcmc;
        }

        public void setKcmc(String kcmc) {
            this.kcmc = kcmc;
        }

        public String getSjbh() {
            return sjbh;
        }

        public void setSjbh(String sjbh) {
            this.sjbh = sjbh;
        }

        public String getKsxs() {
            return ksxs;
        }

        public void setKsxs(String ksxs) {
            this.ksxs = ksxs;
        }

        public String getKscdmc() {
            return kscdmc;
        }

        public void setKscdmc(String kscdmc) {
            this.kscdmc = kscdmc;
        }

        public String getRownum_() {
            return rownum_;
        }

        public void setRownum_(String rownum_) {
            this.rownum_ = rownum_;
        }
    }
}
