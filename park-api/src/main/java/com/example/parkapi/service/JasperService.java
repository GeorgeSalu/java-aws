package com.example.parkapi.service;

import org.springframework.stereotype.Service;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class JasperService {
    private final ResourceLoader resourceLoader;
    private final DataSource dataSource;
    private Map<String, Object> params = new HashMap<>();
    private static final String JASPER_DIRETORIO = "classpath:reports/";
    
    public JasperService(ResourceLoader resourceLoader, DataSource dataSource, Map<String, Object> params) {
		this.resourceLoader = resourceLoader;
		this.dataSource = dataSource;
		this.params = params;
	}

	public void addParams(String key, Object value) {
        this.params.put("IMAGEM_DIRETORIO", JASPER_DIRETORIO);
        this.params.put("REPORT_LOCALE", new Locale("pt", "BR"));
        this.params.put(key, value);
    }

    public byte[] gerarPdf() {
        byte[] bytes = null;
        try {
            Resource resource = resourceLoader.getResource(JASPER_DIRETORIO.concat("estacionamentos.jasper"));
            InputStream stream = resource.getInputStream();
            JasperPrint print = JasperFillManager.fillReport(stream, params, dataSource.getConnection());
            bytes = JasperExportManager.exportReportToPdf(print);
        } catch (IOException | JRException | SQLException e) {
            throw new RuntimeException(e);
        }
        return bytes;
    }
}
