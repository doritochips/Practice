package linux.find;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class TypeCriteria extends BasicSearchCriteria {

    private Type t;

    public static Type typeMap(String s) {
        return s.equals("f") ? Type.FILE : Type.DIR;
    }

    public TypeCriteria(SearchCriteria s, Type t) {
        super(s);
        this.t = t;
    }

    @Override
    public List<File> filter(List<File> list) {
        if(t.equals(Type.FILE)) {
            return s.filter(
                list
                    .stream()
                    .filter(File::isFile)
                    .collect(Collectors.toList()));
        }else{
            return s.filter(
                list
                    .stream()
                    .filter(f -> !f.isFile())
                    .collect(Collectors.toList())
            );
        }
    }
}
