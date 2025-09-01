package com.academiadev.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

public class GenericCsvExporter {
    
    public static <T> String exportToCsv(List<T> data, List<String> selectedColumns) {
        if (data == null || data.isEmpty()) {
            return "Nenhum dado para exportar";
        }
        
        Class<?> clazz = data.get(0).getClass();
        StringBuilder csv = new StringBuilder();
        
        // Cabeçalho
        csv.append(String.join(",", selectedColumns)).append("\n");
        
        // Dados
        for (T item : data) {
            List<String> row = selectedColumns.stream()
                    .map(column -> getFieldValue(item, column))
                    .collect(Collectors.toList());
            csv.append(String.join(",", row)).append("\n");
        }
        
        return csv.toString();
    }
    
    private static String getFieldValue(Object obj, String fieldName) {
        try {
            // Tentar usar getter primeiro
            String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Method getter = obj.getClass().getMethod(getterName);
            Object value = getter.invoke(obj);
            return value != null ? value.toString() : "";
        } catch (Exception e) {
            try {
                // Tentar acessar campo diretamente
                Field field = obj.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                Object value = field.get(obj);
                return value != null ? value.toString() : "";
            } catch (Exception ex) {
                return "N/A";
            }
        }
    }
    
    public static <T> String exportToCsv(List<T> data) {
        if (data == null || data.isEmpty()) {
            return "Nenhum dado para exportar";
        }
        
        Class<?> clazz = data.get(0).getClass();
        Field[] fields = clazz.getDeclaredFields();
        
        List<String> allColumns = List.of(fields).stream()
                .map(Field::getName)
                .collect(Collectors.toList());
        
        return exportToCsv(data, allColumns);
    }
}
