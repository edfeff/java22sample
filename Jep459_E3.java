
public class Jep459_E3 {
    record Sample() implements StringTemplate.Processor<Object, Exception> {
        @Override
        public Object process(StringTemplate st) throws Exception {
            System.out.println(st.fragments()); //[A ,  B , ]
            System.out.println(st.values());//[1, 2]
            return st.interpolate();
        }
    }

    public static void main(String[] args) throws Exception {
        var sample = new Sample();
        var a = 1;
        var b = 2;
        Object s = sample."A \{a} B \{b}";
        System.out.println(s); //A 1 B 2
    }
}
