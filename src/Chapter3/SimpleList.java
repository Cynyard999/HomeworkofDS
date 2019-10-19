package Chapter3;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Union;

import java.util.ArrayList;
import java.util.List;

public class SimpleList {
    public static void main(String[] args){
        SimpleList simpleList = new SimpleList();
        ArrayList<Integer> arrayList1 = new ArrayList();
        ArrayList<Integer> arrayList2 = new ArrayList();
        arrayList1.add(10);
        arrayList1.add(20);
        arrayList1.add(30);
        arrayList1.add(40);
        arrayList2.add(1);
        arrayList2.add(2);
        arrayList2.add(9);
        arrayList2.addAll(arrayList1);
        System.out.println(arrayList1);
        System.out.println(arrayList2);
        System.out.println(simpleList.Union(arrayList1,arrayList2));
    }
    public List intersection(List<Integer> a, List<Integer> b) {
        List<Integer> c = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < a.size() && j < b.size()) {
            if (a.get(i) < b.get(j)) {
                i++;
            } else if (a.get(i) > b.get(j)) {
                j++;
            } else {
                c.add(a.get(i));
                i++;
                j++;
            }
        }
        return c;
    }
    public List Union(List<Integer> a, List<Integer> b){
        List<Integer> c = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < a.size() && j < b.size()) {
            if (a.get(i) < b.get(j)) {
                c.add(a.get(i));
                i++;
            } else if (a.get(i) > b.get(j)) {
                c.add(b.get(j));
                j++;
            } else {
                c.add(a.get(i));
                i++;
                j++;
            }
        }
        //添加多余部分的值
        while (i<a.size()){
            c.add(a.get(i));
            i++;
        }
        while (j<b.size()){
            c.add(b.get(j));
            j++;
        }
        return c;



    }
}
