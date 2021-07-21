package com.basis.campina.xdocumentos.service;

import com.basis.campina.xdocumentos.config.ApplicationProperties;
import com.basis.campina.xdocumentos.domain.dto.ArquivoDTO;
import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class ArquivoService {

    private final MinioClient minioClient;
    private final ApplicationProperties appProperties;

    @SneakyThrows
    public String salvar(ArquivoDTO arquivoDto) {
        minioClient.putObject(PutObjectArgs.builder()
                .stream(new ByteArrayInputStream(arquivoDto.getFile().getBytes()), arquivoDto.getFile().getBytes().length, 0)
                .contentType(StandardCharsets.UTF_8.toString())
                .bucket(appProperties.getBucket())
                .object(arquivoDto.getUuid())
                .build());
        return arquivoDto.getUuid();
    }

    @SneakyThrows
    public ArquivoDTO getDocument(String uuid) {
        GetObjectResponse file = minioClient.getObject(GetObjectArgs.builder()
                .bucket(appProperties.getBucket())
                .object(uuid)
                .build());
        return new ArquivoDTO(uuid, IOUtils.toString(file, StandardCharsets.UTF_8.toString()));
    }

    @SneakyThrows
    public void deletar(String uuid) {
        minioClient.removeObject(RemoveObjectArgs.builder()
                .bucket(appProperties.getBucket())
                .object(uuid)
                .build());
    }
}
