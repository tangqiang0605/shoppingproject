package com.example.demo.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.example.demo.domain.Src;
import com.example.demo.service.SrcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("src")
public class SrcController {

    @Value("${server.port}")
    private String port;

//    public

    @Autowired
    private SrcService srcService;

    private static final String ip="http://localhost:";

//    @PostMapping("testpost")
//    public void postTest(@RequestBody Map<String,Object> postContext){
////        return (Integer)postContext.get("sid") + (Integer)postContext.get("gid");
//        System.out.println(postContext.get("sid"));
////        return sid+gid;
//    }

//    @GetMapping("findAll")
//    public List<Src> findAll(){
//        return srcService.findAll();
//    }

    @PostMapping("upload")
    public int upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String flag= IdUtil.fastSimpleUUID();
        String rootFilePath=System.getProperty("user.dir")+"\\springbootdemo1\\src\\main\\resources\\files\\"+ flag+"_"+originalFilename;
        FileUtil.writeBytes(file.getBytes(),rootFilePath);
//        return ip+port+"/src/"+flag;

        Src src=new Src(null,ip+port+"/src/"+flag,rootFilePath);
        return srcService.add(src);

    }

    @GetMapping("{flag}")
    public String getFiles(HttpServletResponse response, @PathVariable String flag){
        OutputStream os;
        String basePath=System.getProperty("user.dir")+"\\springbootdemo1\\src\\main\\resources\\files\\";
        List<String> fileNames=FileUtil.listFileNames(basePath);
        String file = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");

        try{
            if(StrUtil.isNotEmpty(file)){

                response.addHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(file,"UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes=FileUtil.readBytes(basePath+file);
                os=response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();


            }

            return file;

        }catch (Exception e){
            return "文件下载失败";
        }


    }
}
