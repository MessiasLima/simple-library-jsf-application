package br.com.sotechsaude.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Random;

public class Utils
{
	public static Date converterData(String value)
	{
		String valor = ""; 
		if (value == null || value.equals(""))
		{
			return null;
		}
		else
		{
			if (value.trim().length() == 10)
				valor = value + " 00:00:00";
			else
				valor = value;
			try
			{
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				formato.setLenient(false);
				Date data = formato.parse(valor);
				return data;
			}
			catch (Exception e)
			{
				return null;
			}
		}
	}

	public static String formatar (String texto, String posicao, String caractere, int tamanho)
	{  
		if (posicao == "E")
		{
			while (texto.length() < tamanho)
			{  
				texto = caractere + texto;  
			}
		}
		else
		{
			while (texto.length() < tamanho)
			{  
				texto = texto + caractere;  
			}
		}
		return texto;
	}

	public static String formataData(Date value)
	{
		if (value == null || value.equals(""))  
			return null;
		else
		{
			try
			{
				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
				return formato.format(value);
			}
			catch (Exception e)
			{
				return null;
			}
		}
	}

	public static String formataHora(Date value)
	{
		if (value == null || value.equals(""))  
			return null;
		else
		{
			try
			{
				SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
				return formato.format(value);
			}
			catch (Exception e)
			{
				return null;
			}
		}
	}

	public static String formataDataHora(Date value)
	{
		if (value == null || value.equals(""))  
			return null;
		else
		{
			try
			{
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy'T'HH:mm:ss");
				return formato.format(value);
			}
			catch (Exception e)
			{
				return null;
			}
		}
	}

	public static String formataValor(String value)
	{
		if (value == null || value.equals(""))  
			return null;
		else
		{
			try
			{
				value = value.replace(".", "");
				value = value.replace("/", "");
				value = value.replace("-", "");
				value = value.replace(",", ".");
				return value;
			}
			catch (Exception e)
			{
				return null;
			}
		}
	}

	public static String converterValor(Double value, int decimais) throws ParseException
	{
		Locale locale = new Locale("pt", "BR");
		String formato = "";
		switch (decimais)
		{
		case 0:  
			formato = "###,###,##0";
			break;
		case 1:
			formato = "###,###,##0.0";
			break;
		case 2:
			formato = "###,###,##0.00";
			break;
		case 3:
			formato = "###,###,##0.000";
			break;
		case 4:
			formato = "###,###,##0.0000";
			break;
		case 5:
			formato = "###,###,##0.00000";
			break;
		default:
			formato = "###,###,##0.00";
			break;
		}
		DecimalFormat formatodecimal = formato == null ? new DecimalFormat("###,###,##0.00") : new DecimalFormat(formato);  
		formatodecimal.setDecimalFormatSymbols(new DecimalFormatSymbols(locale));
		formatodecimal.setGroupingSize(3);
		formatodecimal.setGroupingUsed(true);
		String resultado = formatodecimal.format(value);
		return resultado;
	}

	public static String md5(String value)
	{
		String retorno = "";
		MessageDigest md = null;
		try
		{
			md = MessageDigest.getInstance("MD5");
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		BigInteger hash = new BigInteger(1, md.digest(value.getBytes()));
		retorno = hash.toString(16);
		return retorno;
	}
	
	public static void salvarArquivo(String conteudo, String nomearquivo)
	{
		try
		{
			File arquivo = new File(nomearquivo);
			BufferedWriter saida = new BufferedWriter(new FileWriter(arquivo));
			saida.write(conteudo);
			saida.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void getDiretorio (String diretorio)
	{
		File diretorios = new File(diretorio);
		if (!diretorios.exists())
		{
			diretorios.mkdirs();
		}
	}

	public static boolean isCNPJ(String CNPJ) {

	    if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") ||
	        CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333") ||
	        CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555") ||
	        CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") ||
	        CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999") ||
	       (CNPJ.length() != 14))
	       return(false);
	
	    char dig13, dig14;
	    int sm, i, r, num, peso;
	    
	    try {
	      sm = 0;
	      peso = 2;
	      for (i=11; i>=0; i--) {
	        num = (int)(CNPJ.charAt(i) - 48);
	        sm = sm + (num * peso);
	        peso = peso + 1;
	        if (peso == 10)
	           peso = 2;
	      }

	      r = sm % 11;
	      if ((r == 0) || (r == 1))
	         dig13 = '0';
	      else dig13 = (char)((11-r) + 48);

	      sm = 0;
	      peso = 2;
	      for (i=12; i>=0; i--) {
	        num = (int)(CNPJ.charAt(i)- 48);
	        sm = sm + (num * peso);
	        peso = peso + 1;
	        if (peso == 10)
	           peso = 2;
	      }

	      r = sm % 11;
	      if ((r == 0) || (r == 1))
	         dig14 = '0';
	      else dig14 = (char)((11-r) + 48);

	      if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13)))
	         return(true);
	      else return(false);
	    } catch (InputMismatchException erro) {
	        return(false);
	    }
    }
	public static Integer gerarNumeroAleatorio(int numero){
		Random aleatorio = new Random();
		return aleatorio.nextInt(numero);
	}
	
	public static Timestamp pegarDataHoraAtual(){
		return new Timestamp(System.currentTimeMillis());
	}
	
}