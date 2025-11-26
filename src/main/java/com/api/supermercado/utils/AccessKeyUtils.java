package com.api.supermercado.utils;

import com.api.supermercado.dtos.AccessKeyDTO;
import com.api.supermercado.entities.IssuingCompany;

import java.time.LocalDate;

public class AccessKeyUtils {



    /**
     * Calcula el dígito verificador usando algoritmo Módulo 11 (SRI)
     */
    public static String obtenerDigitoVerificador(String clave) {
        int[] coef = {2, 3, 4, 5, 6, 7};
        int suma = 0;

        for (int i = clave.length() - 1, j = 0; i >= 0; i--, j++) {
            int digito = Character.getNumericValue(clave.charAt(i));
            suma += digito * coef[j % coef.length];
        }

        int mod = 11 - (suma % 11);

        if (mod == 11) return "0";
        if (mod == 10) return "1";
        return String.valueOf(mod);
    }

    /**
     * Genera la clave de acceso según especificación del SRI
     */
    public static String generarClaveAcceso(AccessKeyDTO dto, IssuingCompany issuingCompany) {

        // 1️⃣ Fecha actual del sistema (SIEMPRE del backend)
        LocalDate fecha = LocalDate.now();
        String dd = String.format("%02d", fecha.getDayOfMonth());
        String mm = String.format("%02d", fecha.getMonthValue());
        String yyyy = String.valueOf(fecha.getYear());

        // 2️⃣ Construcción de la clave base en el ORDEN exacto del SRI
        String base =
                dd +
                        mm +
                        yyyy +
                        dto.documentType().getCode() +   // 01
                        dto.ruc() +            // RUC del emisor
                        dto.environment() +    // ambiente
                        issuingCompany.getSeries() +// 001001
                        dto.sequential() +     // 000000123
                        dto.numericCode() +    // aleatorio
                        dto.emissionType();    // 1

        // 3️⃣ Calcular dígito verificador (Módulo 11)
        String verificador = obtenerDigitoVerificador(base);

        // 4️⃣ Retornar clave completa
        return base + verificador;
    }
}
