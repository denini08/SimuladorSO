# SimuladorSO
Simulação algoritmo de paginação LRU

README - SO

SIMULADOR DE ALGORITMO LRU

Caminho do código: FAB.DE ENTRADAS-> MAIN -> PROCESSO -> MMU-> LISTA-> STATUS

**TIPOS DE MEMÓRIA UTILIZADOS NO PROGRAMA:**
- MemFis: simula a memória RAM do SO, é um array 							
- MemVirt: array de páginas com o dobro de espaços da memória física //CONTÉM: 
- MemHD: Um array que simula um disco rígido de um computador 

**O QUE É A MMU E COMO FUNCIONA?**
	Memory Manager Unit - unidade gerenciadora de memória. 
  
  A CPU envia endereços virtuais à MMU e ela retorna endereços físicos à memória.

**ESTRUTURA DA TABELA DE PAGINAÇÃO (só as requisitadas):**
	* Modificada: Indica se a página já foi modificada ou não
	* Moldura: endereço do local que ela está, numero que corresponde a identificação da página na Memória RAM
	* Referenciada: se foi lida por algum outro processo
	* Presente/Ausente: se está no lugar que procura, para nós a RAM
	/*Presente é um estado quando o dado se encontra na memória RAM, e Ausente quando se está no HD ou quando a página está nula;*/
