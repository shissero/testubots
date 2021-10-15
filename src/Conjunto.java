import java.util.ArrayList;
import java.util.List;

public class Conjunto {
    public static <T> List<T> intersecao(List<T> lista1, List<T> lista2){

        List<T> intersecao = new ArrayList<>();

        for(T a : lista1){

            if(pertence(a ,intersecao)) continue;

            for(T b : lista2){

                if(pertence(b, intersecao)) continue;

                if(a.equals(b) && !pertence(a, intersecao)){
                    intersecao.add(b);
                }
            }
        }

        return intersecao;
    }

    @SafeVarargs
    public static <T> List<T> uniao (List<T>... lista){

        List<T> uniao = new ArrayList<>();

        for(List<T> a : lista){
            for(T b : a){
                if(!pertence(b, uniao)) uniao.add(b);
            }
        }

        return uniao;
    }

    public static <T> boolean pertence(T object, List<T> lista){

        if(lista.isEmpty()) return false;
        else {
            for(T a : lista) if(a.equals(object)) return true;
        }

        return false;
    }
}
