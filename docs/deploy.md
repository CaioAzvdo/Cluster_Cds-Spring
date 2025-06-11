## 🚀 Deploy Automatizado com GitHub Actions + AWS (EC2/RDS) + Docker

Este projeto conta com uma automação completa de deploy utilizando **GitHub Actions**, **Docker** e **AWS** (EC2 + RDS). Abaixo está um resumo do processo implementado:

### 🧩 Estrutura de Deploy

- **Branch exclusiva para deploy:**  
  Criada para isolar e organizar alterações relacionadas à publicação em produção.

- **GitHub Actions Workflow:**  
  Configurado em `.github/workflows/prod.yml` e dividido em dois jobs:

    1. **Build**
        - Setup do ambiente Java
        - Build da aplicação Spring Boot
        - Criação da imagem Docker
        - Push da imagem para o **Docker Hub**

    2. **Deploy** (executado após o build)
        - Pull da imagem mais recente do Docker Hub
        - Remoção do container antigo
        - Execução de novo container com a imagem atualizada

- **Dockerfile:**  
  Presente na raiz do projeto, responsável por construir a imagem e executar o `.jar`.

- **Variáveis de Ambiente:**  
  Configuradas de forma segura com **GitHub Actions Secrets**, evitando exposição de dados sensíveis.

- **Runner Auto-Hospedado:**  
  Utilização de um **GitHub Actions Runner** rodando em uma instância **EC2 (Ubuntu)** para executar o processo de deploy.

### 🗄️ Banco de Dados com Amazon RDS

- Criação de uma instância **PostgreSQL** no **RDS**
- Conexão com a EC2 feita através de uma **VPC própria**
- Configuração de **Security Groups** para garantir a comunicação entre EC2 e RDS de forma segura

---

Com esse setup, o projeto conta com um pipeline de CI/CD robusto, automatizado e seguro, facilitando atualizações contínuas no ambiente de produção.

### 🛠️ Tecnologias Utilizadas

- Spring Boot
- GitHub Actions
- Docker
- Docker Hub
- AWS EC2
- AWS RDS (PostgreSQL)
- GitHub Actions Runner
- VPC e Security Groups

---

> Esse processo segue boas práticas de DevOps e oferece escalabilidade, segurança e agilidade no ciclo de desenvolvimento e entrega contínua.
