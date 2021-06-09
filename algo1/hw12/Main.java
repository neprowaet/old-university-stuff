import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        String whole = "123vcpas45_1234567890123444444444_56789";
        List<String> parts = new ArrayList<>();
        parts.add(0, "pas");
        parts.add(1, "4567890123444444444");
        //parts.add(2, "assssssssssssssssssssssssss");

        IntPair i = substring(whole, parts);
        if(i!=null)
        System.out.println(i.getFirst()+ ", " + i.getSecond());
    }

    public static IntPair substring(String whole, List<String> parts) {
        long[] hashes = new long[parts.size()];
        for (int i = 0; i<parts.size();i++) {
            if(parts.get(i).length() > whole.length()) return null;
            hashes[i] = hash(parts.get(i));
        }

        long[] whashes = new long[parts.size()];
        for (int i = 0; i < whole.length(); i++) {
            for (int j = 0; j < parts.size(); j++) {
                String p = parts.get(j);

                long h = 1;
                for (int k = 1; k <= p.length()-1; k++) {
                    h <<= 8;
                    h %= q;
                }

                if(i + p.length() > whole.length()) break;

                if(i==0) whashes[j] = hash(whole.substring(0, p.length()));
                else whashes[j] = newhash(whashes[j], whole.charAt(i-1), whole.charAt(i + p.length()-1),p.length(),h);

                if(hashes[j] == whashes[j]) {
                    boolean bool= true;
                    for(int k=0;k<p.length();k++) {
                        if (whole.charAt(k+i) != parts.get(j).charAt(k)) {
                            bool=false; break;
                        }
                    }
                    if (bool) return new IntPair(i,j);
                }
              }
        }
        return null;
    }

    public static final int q = 31;

    public static long hash (String s){
        long r = 0;
        for(int i = 0;i < s.length();i++ )
            r = ((r << 8) + (s.charAt(i) & 255)) % q;

        return r;
    }

    public static long newhash (long h, char a, char b, int l, long nh){
        long r = 0;
        r = (h - nh * (a & 255)) % q;
        if (r < 0) r += q;
        r = ((r << 8) + (b & 255)) % q;

        return r;
    }


}
