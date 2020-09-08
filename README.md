# PrinterGuard
PrinterGuard project for monitoring 3d printer and performing remote actions with it.

ENG

1. You need to set fixed, static IP address on RaspberryPi (ipv4 is the best choice, but depends on your network).

2. Probably on RaspberryPI the is no java version 11 installed, so check it and if no:

	sudo apt-get install openjdk-11-jdk

	If that command doesn't work, you need to find another one.
	
3. RaspberryPi needs to have gpio command line tool. It may be default installed, but if no: 
    
    sudo apt-get install wiringpi
	
4. You can run my app using command java -jar PrinterGuard.jar, while you are in right directory (on RaspberryPi).

5. If you want to have access outside your local network, you need to add new settings (NAT) on your router :
	- external port 11111 to raspberry IP, internal port 8080 (camera)
	- external port 11110 to raspberry IP, internal port 80 (application)

6. You can use app by going to external router IP and port 11110, e.g 80.20.210.20:11110


PL
1. Na raspberry trzeba ustawić stały adres IP (najlepiej ipv4).

2. Prawdopodobnie na raspberry nie ma Javy w wersji 11, dlatego w terminalu wykonujemy polecenie:

	sudo apt-get install openjdk-11-jdk

	Jeśli nie zadziała to trzeba poszukać innego.
	
3. Potrzebne jest na raspberry polecenie gpio - z paczki wiringpi. Możliwe, że jest zainstalowane
    domyślnie, lecz jeśli nie, należy użyć polecenia: 
    
    sudo apt-get install wiringpi
	
4. Aplikację uruchamiamy poleceniem java -jar PrinterGuard.jar, będąc w katalogu, w którym się ona znajduje (na raspberry).

5. Ustawienie przekierowania (NAT) na routerze:
	- port zewnętrzny 11111 na adres IP raspberry, port wewnętrzny 8080 (ustawienia kamery)
	- port zewnętrzny 11110 na adres IP raspberry, port wewnętrzny 80 (ustawienia aplikacji, to jest domyślny port)

6. Używamy aplikacji poprzez wejście na zewnętrzny adres IP routera i port 11110, np. 80.20.210.20:11110

