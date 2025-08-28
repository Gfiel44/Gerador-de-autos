
# 📄 Sistema de Geração de Autos de Arrematação

## 🚀 Sobre o Projeto
Este projeto tem como objetivo **automatizar a geração de autos de arrematação** utilizados em processos de leilão judicial.  
Atualmente, o preenchimento desses autos é feito manualmente.  
Com esta aplicação, o processo será automatizado, permitindo **economia de tempo e redução de erros**.

---

## ⚙️ Funcionalidades
- Leitura de relatórios em **PDF** utilizando **Apache PDFBox**  
- Extração de informações relevantes (ex: dados do arrematante, vara, juiz, valor, etc.)  
- Preenchimento automático de modelos em **Word (.docx)** via **Apache POI**  
- Suporte a **múltiplos modelos de auto** (veículos, imóveis, etc.)  
- Interface gráfica em **JavaFX** para facilitar o uso por qualquer colaborador  
- Exportação do auto final em formato `.docx`

---

## 🛠️ Tecnologias Utilizadas
- **Java 17**  
- **Maven** (gerenciamento de dependências)  
- **Apache POI** → manipulação de arquivos Word (.docx)  
- **Apache PDFBox** → leitura de arquivos PDF  
- **JavaFX** → interface gráfica  

---

## 📂 Estrutura de Pastas
```
src/main/java/br/com/gabriel/auto/
├── modelo/       # Classes de dados (AutoDados, TipoAuto)
├── leitor/       # Classe LeitorPDF
├── gerador/      # Classe AutoGenerator
├── ui/           # Interface em JavaFX
└── Main.java     # Ponto de entrada da aplicação
```

---

## 📌 Roadmap
- [x] Configuração inicial do projeto (Maven + dependências)  
- [ ] Implementação da classe `AutoDados`  
- [ ] Implementação da leitura de relatórios PDF (`LeitorPDF`)  
- [ ] Geração de documentos Word a partir de modelos (`AutoGenerator`)  
- [ ] Criação da interface em JavaFX (`MainView`)  
- [ ] Suporte à escolha de múltiplos modelos de autos  

---

## 👨‍💻 Autor
Projeto desenvolvido por **Gabriel Fiel** com o objetivo de praticar Java e automatizar um processo real de trabalho.
