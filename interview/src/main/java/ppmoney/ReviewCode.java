package ppmoney;

/**
 * @ClassName ReviewCode
 * @Description review
 * @Author qzli
 * @Date 2019/7/23 20:37
 * @Version 1.0
 **/
public class ReviewCode {
    
    public static Boolean judgeLngLaxfilterByReview(int lng,int lax,int lngMax,int lngMin, int laxMax,int laxMin){
    
        if (lngMax != 0 && lng > lngMax) {
            return true;
        }
    
        if (laxMax != 0 && lax > laxMax) {
            return true;
        }
    
        if (lngMin != 0 && lngMin > laxMax) {
            return true;
        }
    
        if(laxMin==0 || laxMax>=laxMin){
            return true;
        }
        return false;
    }
    
    public static Boolean judgeLngLaxfilter(int lng,int lax,int lngMax,int lngMin, int laxMax,int laxMin){
        boolean flag =true;
        boolean lngFlag1 = false;
        boolean lngFlag2 = false;
        boolean laxFlag1 = false;
        boolean laxFlag2= false;
        if(lngMax==0){
            lngFlag1 = true;
        }else if(lng <=lngMax){
            lngFlag1 = true;
        }
        if(laxMax==0){
            laxFlag1 = true;
        }else if(lax<=laxMax){
            laxFlag1 = true;
        }
        if(lngMin==0){
            lngFlag2 = true;
        }else if(lngMin>=lngMax){
            lngFlag2 = true;
        }
        if(laxMin==0){
            laxFlag2= false;
        }else if(laxMax>=laxMin){
            laxFlag2= false;
        }
        //这里按道理来说就是反过来就是任意一个为false
        if(lngFlag1 && lngFlag2 &&
                laxFlag1 && laxFlag2){
            flag = false;
        }
        return flag;
    }
}
