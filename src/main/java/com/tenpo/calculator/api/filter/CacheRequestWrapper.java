package com.tenpo.calculator.api.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class CacheRequestWrapper extends HttpServletRequestWrapper {

   private final String body;

   public CacheRequestWrapper(HttpServletRequest request) throws IOException {
      super(request);
      StringBuilder stringBuilder = new StringBuilder();
      BufferedReader bufferedReader = null;
      try {
         InputStream inputStream = request.getInputStream();
         if (inputStream != null) {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            char[] charBuffer = new char[128];
            int bytesRead = -1;
            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
               stringBuilder.append(charBuffer, 0, bytesRead);
            }
         }
      } catch (IOException ex) {
         throw ex;
      } finally {
         if (bufferedReader != null) {
            try {
               bufferedReader.close();
            } catch (IOException ex) {
               throw ex;
            }
         }
      }
      body = stringBuilder.toString();
   }

   @Override
   public ServletInputStream getInputStream() throws IOException {
      final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8));
      ServletInputStream servletInputStream = new ServletInputStreamImpl(byteArrayInputStream);
      return servletInputStream;
   }

   @Override
   public BufferedReader getReader() throws IOException {
      return new BufferedReader(new InputStreamReader(this.getInputStream(), StandardCharsets.UTF_8));
   }

   public String getBody() {
      return this.body;
   }

   static final class ServletInputStreamImpl extends ServletInputStream {

      final ByteArrayInputStream byteArrayInputStream;

      ServletInputStreamImpl(ByteArrayInputStream byteArrayInputStream) {
         this.byteArrayInputStream = byteArrayInputStream;
      }

      @Override
      public boolean isFinished() {
         return false;
      }

      @Override
      public boolean isReady() {
         return false;
      }

      @Override
      public void setReadListener(ReadListener readListener) {

      }

      public int read() throws IOException {
         return byteArrayInputStream.read();
      }
   }
}
