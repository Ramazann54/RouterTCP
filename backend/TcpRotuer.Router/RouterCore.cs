using System;
using System.Net;
using System.Net.Sockets;
using TcpRouter.Abstractions;
using TcpRouter.RouteManager;
using TcpRouter.Stubs;

namespace TcpRotuer.Router
{
  /// <summary>
  /// Модуль маршрутизации
  /// </summary>
  public class RouterCore
  {
    
    private readonly ISerialNumberDetector _detector;
    private readonly IRouteManagerForRouter _routerManager;
    private readonly IPAddress _localAddress = IPAddress.Parse("127.0.0.1");
    private readonly ushort _tcpPortForListen;
    /// <summary>
    /// на вход получаете
    /// 1) интерфейс модуля определятора
    /// 2) интерфейс модуля менеджера конфигурации
    /// 3) номер tcp порта
    /// </summary>
    /// <param name="detector"></param>
    /// <param name="routerManager"></param>
    /// <param name="tcpPortForListen"></param>
    /// <exception cref="ArgumentNullException"></exception>
    /// <exception cref="NotImplementedException"></exception>
    public RouterCore(ISerialNumberDetector detector,
      IRouteManagerForRouter routerManager,
      ushort tcpPortForListen)
    {
      _detector = detector ?? throw new ArgumentNullException(nameof(detector));
      _routerManager = routerManager ?? throw new ArgumentNullException(nameof(routerManager));
      _tcpPortForListen = tcpPortForListen;

      throw new NotImplementedException();

      /* Внимание!
       * 
       * До тех пор, пока твой коллега не напишет Определятор, 
       * который будет работать с реальным счётчиком,
       * используй фейковый детектор, который лежит в проекте 
       * Utils\TcpRouter.FakeDetector
       * 
       * В качестве "фейкового счётчика" используй приложение из проекта 
       * Utils\TcpRouter.FakeMeter
       * 
       * FakeMeter и FakeDetector - созданы друг для друга
       */

      /* Когда нужно написать нечто сложное, полезно описать алгоритм на всевдо-коде,
       * как ниже:
       * 
       * Слушаем tcp порт
       * если кто-то подключился, то
       * создаём новый поток
       * дальнейшую работу делаем в новом потоке
       * отдаём соединение определятору
       * если определятор не смог определить серийный номер, то
       *    закрываем соединение
       * 
       * если определятор смог определить серийный номер, то
       * спрашиваем в менеджере конфигурации маршрут для серийного номера
       * если маршрута нет, то 
       *    закрываем соединение
       *    
       * если маршрут есть, то
       * подключаемся к системе сбора данных
       * после подключения к системе сбора данных (ССД)
       *  передаём данные от ССД к устройству
       *  передаём данные от устройства к ССД
       */
    }

    private void _start()
    {
      TcpListener server = new TcpListener(_localAddress, _tcpPortForListen);
      
      server.Start();

      while (true)
      {
        Console.WriteLine("Waiting for connections...");
        Socket newSocket = server.AcceptSocket();
        new Thread(() =>
        {
          FakeDetector fakeDetector = new FakeDetector();
          if (!fakeDetector.TryReadDeviceInfo(newSocket, out var serialNumber))
          {
            newSocket.Close();
            return;
          }

          RouteManager routeManager = new RouteManager();

          if (!routeManager.TryGetRoute(serialNumber, out var route))
          {
            return;
          }
            
          // Система сбора данных? Куда именно подключаться?
          
        }).Start();
      }
      
    }


  }
}