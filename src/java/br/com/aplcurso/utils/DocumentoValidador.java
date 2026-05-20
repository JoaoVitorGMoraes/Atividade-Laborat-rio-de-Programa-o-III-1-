package br.com.aplcurso.utils;

public class DocumentoValidador {

    public static boolean isCPF(String cpf) {

        cpf = cpf.replaceAll("[^\\d]", "");

        if (cpf.length() != 11
                || cpf.matches("(\\d)\\1{10}")) {

            return false;
        }

        try {

            int d1 = 0;
            int d2 = 0;

            for (int i = 0; i < 9; i++) {

                int digito = cpf.charAt(i) - '0';

                d1 += digito * (10 - i);
                d2 += digito * (11 - i);
            }

            d1 = 11 - (d1 % 11);

            if (d1 > 9) {

                d1 = 0;
            }

            d2 += d1 * 2;

            d2 = 11 - (d2 % 11);

            if (d2 > 9) {

                d2 = 0;
            }

            return d1 == (cpf.charAt(9) - '0')
                    && d2 == (cpf.charAt(10) - '0');

        } catch (Exception e) {

            return false;
        }
    }

    public static boolean isEmailValido(String email) {

        if (email == null
                || email.trim().isEmpty()) {

            return false;
        }

        return email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        );
    }
}