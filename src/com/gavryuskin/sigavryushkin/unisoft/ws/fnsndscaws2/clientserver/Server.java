package com.gavryuskin.sigavryushkin.unisoft.ws.fnsndscaws2.clientserver;
import com.gavryuskin.sigavryushkin.unisoft.ws.fnsndscaws2.webserver.FNSNDSCAWS2;
import com.gavryuskin.sigavryushkin.unisoft.ws.fnsndscaws2.webserver.FNSNDSCAWS2Port;
import com.gavryuskin.sigavryushkin.unisoft.ws.fnsndscaws2.request.NdsRequest2;
import com.gavryuskin.sigavryushkin.unisoft.ws.fnsndscaws2.request.ObjectFactory;
import java.util.List;
import java.util.Scanner;

/**
 Created:Gavryuskin S.I. 19.07.2019
 e-mail:parsesignal@yandex.ru
 */

public class Server {

        private static FNSNDSCAWS2 fsn = new FNSNDSCAWS2();
        private static FNSNDSCAWS2Port port = fsn.getFNSNDSCAWS2Port();
        private static String x = null;
        private static NdsRequest2 req = new NdsRequest2();
        private static List<NdsRequest2.NP> list = req.getNP();
        private static NdsRequest2.NP np = new ObjectFactory().createNdsRequest2NP();
        private static Scanner in = new Scanner(System.in);

        public static void main(String[] args)  {

          while(true) {
              System.out.println("Если нужно ввести данные юридического лица введите \"1\", " +
                                 "если данные ИП введите \"2\":");
              if (in.nextLine().equals("1")) {
                  checkUL();

              } else {
                  checkFL();
              }
          }
        }

   private static  void checkUL(){
           do {
               System.out.println("Введите ИНН (обязательное поле):");
               x = in.nextLine();
           }
           while (x.isEmpty());
           np.setINN(x);
           System.out.println("Введите КПП (при наличии):");
            x = in.nextLine();
           if (x.isEmpty()) {
               System.out.println("Дата сделки (при наличии):");
           } else {
               np.setKPP(x);
               System.out.println("Дата сделки (при наличии):");
           }
           x = in.nextLine();
           if (x.isEmpty()) {
               list.add(np);
               System.out.format("Данные по ИНН Юридического лица:%s ,Статус:%s%n",np.getINN(),
                       new ResponseVariant().getparametrUL(port.ndsRequest2(req).getNP().get(0).getState()).toUpperCase());
           } else {
               np.setDT(x);
               list.add(np);
               System.out.format("Данные по ИНН Юридического лица:%s ,Статус:%s%n",np.getINN(),
                       new ResponseVariant().getparametrUL(port.ndsRequest2(req).getNP().get(0).getState()).toUpperCase());
           }

       }

    private static void checkFL(){
                do {
                    System.out.println("Введите ИНН (обязательное поле):");
                    x = in.nextLine();
                }
                while (x.isEmpty());
                np.setINN(x);
                System.out.println("Дата сделки (при наличии):");
                x = in.nextLine();
                if (x.isEmpty()) {
                    list.add(np);
                    System.out.format("Данные по ИНН ИП:%s ,Статус:%s%n",np.getINN(),
                            new ResponseVariant().getparametrFL(port.ndsRequest2(req).getNP().get(0).getState()).toUpperCase());
                } else {
                    np.setDT(x);
                    list.add(np);
                    System.out.format("Данные по ИНН ИП:%s ,Статус:%s%n",np.getINN(),
                            new ResponseVariant().getparametrFL(port.ndsRequest2(req).getNP().get(0).getState()).toUpperCase());
                }
            }
    }








