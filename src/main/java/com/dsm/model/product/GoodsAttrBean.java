package com.dsm.model.product;

/**
 * 已定义的商品的一个属性，属性值单元
 * 封装一个具体的商品属性名值单元
 *
 * @author lbwwz
 */
public class GoodsAttrBean implements Comparable<GoodsAttrBean> {

    private String attrId;
    private String attrName;
    private String valueId;
    private String valueName;

    // 定义先关的排序顺序
    private int attrSort;
    private int valueSort;

    public String getAttrId() {
        return attrId;
    }

    public void setAttrId(String attrId) {
        this.attrId = attrId;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getValueId() {
        return valueId;
    }

    public void setValueId(String valueId) {
        this.valueId = valueId;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public int getAttrSort() {
        return attrSort;
    }

    public void setAttrSort(int attrSort) {
        this.attrSort = attrSort;
    }

    public int getValueSort() {
        return valueSort;
    }

    public void setValueSort(int valueSort) {
        this.valueSort = valueSort;
    }

    public GoodsAttrBean() {
    }

    ;

    public GoodsAttrBean(String attrId, String attrName, String valueId, String valueName, int attrSort,
                         int valueSort) {
        super();
        this.attrId = attrId;
        this.attrName = attrName;
        this.valueId = valueId;
        this.valueName = valueName;
        this.attrSort = attrSort;
        this.valueSort = valueSort;
    }

    /*
     * ������ǰ��ҳ����ʾ�����跽ʽ��
     *
     * �Ȱ�������˳������
     * Ȼ��������ֵ����
     */
    @Override
    public int compareTo(GoodsAttrBean o) {
        if (this.attrSort > o.attrSort)
            return 1;
        if (this.attrSort == o.attrSort)
            return this.valueSort - o.valueSort;
        return -1;
    }

    @Override
    public String toString() {
        return "GoodsAttrBean [attrId=" + attrId + ", attrName=" + attrName + ", valueId=" + valueId + ", valueName="
                + valueName + ", attrSort=" + attrSort + ", valueSort=" + valueSort + "]";
    }

}
