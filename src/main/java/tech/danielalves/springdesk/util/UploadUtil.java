package tech.danielalves.springdesk.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.multipart.MultipartFile;

public class UploadUtil {

    public static String fazerUploadImagem(MultipartFile imagem){
        String sucessoUpload= "";

        if (!imagem.isEmpty()){
            String nomeArquivo = imagem.getOriginalFilename();
            try {
                //Criando diretorio para armazena arquivo
                String pastaUploadImagem = "src\\main\\resources\\static\\images\\img-uploads";
                File dir = new File(pastaUploadImagem);
                if (!dir.exists()){
                    dir.mkdirs();
                }
                //Criando o arquivo no diretorio
                File serverFile = new File(dir.getAbsolutePath()+File.separator+nomeArquivo);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

                stream.write(imagem.getBytes());
                stream.close();
                String caminhoImagem = serverFile.toString().substring(23, serverFile.toString().length());
                sucessoUpload = caminhoImagem;
                System.out.println("Armazenado: "+serverFile.getAbsolutePath());

            }catch (Exception e){
                System.out.println("Falha em carregar o arquivo "+nomeArquivo  +" => "+ e.getMessage());
            }
        }else{
            System.out.println("Falha em carregar o arquivo. Campo está vazio.");
        }
        return sucessoUpload;
    }
    
}
