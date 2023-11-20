kilometragemAntiga = 0;
kilometragemNova = 0;
litrosAbastecidos = 0;

kilometragemAntiga = int(input("Digite a kilometragem antiga: "));
kilometragemNova = int(input("Digite a kilometragem nova: "));
litrosAbastecidos = int(input("Digite a quantidade de litros abastecidos: "));

consumoMedio = (kilometragemNova - kilometragemAntiga) / litrosAbastecidos;
print(consumoMedio)
