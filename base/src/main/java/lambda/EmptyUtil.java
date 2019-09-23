package lambda;

public class EmptyUtil {
    public static <In,Out> Out handlerEmpty(In in, IAction<In,Out> empty, Out defaultVal) {
        if (in == null) {
            return defaultVal;
        }
        return empty.accept(in);
    }

    public static void main(String[] args) {
        String ssx = "asdf";
        System.out.printf(EmptyUtil.handlerEmpty(ssx, String::toUpperCase, "旮旯").toString());
    }
}
