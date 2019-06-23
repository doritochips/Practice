package linux.find;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: David Zhou
 * @Date: 2019-06-23
 * Implement Linux find: recursively search all files in "Path"
 * Requirement:
 * 1. Support -type: f, d, -
 * 2. Support -name
 */
public class Main {
    /**
     * @param: path, type(f, d, -), name
     * type with "-" will be ignored
     * Sample execution: "./test f home"
     * */
    public static List<File> dfs(SearchCriteria searchCriteria, List<File> files){
        List<File> r = searchCriteria.filter(files);
        for(File f : files) {
            if(!f.isFile()) r.addAll(dfs(searchCriteria, Arrays.asList(f.listFiles())));
        }
        return r;
    }

    public static void main(String[] args) throws Exception {
        if(args.length < 1 || args[0].isEmpty()) throw new Exception("Path needs to be specified");
        File folder = new File(args[0]);

        List<File> rootLayer = Arrays.asList(folder.listFiles());

        SearchCriteria search = new BasicSearchCriteria(null);

        if(args.length >= 2 && !args[1].equals("-")) search = new TypeCriteria(search, TypeCriteria.typeMap(args[1]));
        if(args.length == 3) search = new NameCriteria(search, args[2]);

        List<File> result = dfs(search, rootLayer);

        result.stream()
            .map(File::getName).forEach(System.out::println);
    }
}
