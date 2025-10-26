CREATE TABLE Usuario ( /* base para o login */
    id INT PRIMARY KEY AUTO_INCREMENT,
    uuid CHAR(36) UNIQUE NOT NULL DEFAULT (UUID()),
    email VARCHAR(100) UNIQUE NOT NULL,
    senha_hash VARCHAR(255) NOT NULL,
    tipo ENUM('ALUNO', 'PROFESSOR', 'FUNCIONARIO') NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_email (email)
);

CREATE TABLE Departamento (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL UNIQUE,
    descricao TEXT,
    chefe_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE Curso (
    id INT PRIMARY KEY AUTO_INCREMENT,
    uuid CHAR(36) UNIQUE NOT NULL DEFAULT (UUID()),
    nome VARCHAR(150) NOT NULL,
    codigo VARCHAR(20) UNIQUE NOT NULL,
    departamento_id INT NOT NULL,
    duracao_semestres INT DEFAULT 8,
    ativo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (departamento_id) REFERENCES Departamento(id)
);

CREATE TABLE Professor (
    id INT PRIMARY KEY AUTO_INCREMENT,
    usuario_id INT UNIQUE NOT NULL,
    uuid CHAR(36) UNIQUE NOT NULL DEFAULT (UUID()),
    cpf VARCHAR(14) UNIQUE NOT NULL,
    nome VARCHAR(100) NOT NULL,
    matricula VARCHAR(20) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    id_departamento INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (id_departamento) REFERENCES Departamento(id),
);

/* nao dava para adicionar as foreign keys de uma vez antes (dependencia circular) */ 
ALTER TABLE Departamento 
ADD FOREIGN KEY (chefe_id) REFERENCES Professor(id);

ALTER TABLE Professor 
ADD FOREIGN KEY (id_departamento) REFERENCES Departamento(id);

CREATE TABLE Aluno (
    id INT PRIMARY KEY AUTO_INCREMENT,
    usuario_id INT UNIQUE NOT NULL,
    uuid CHAR(36) UNIQUE NOT NULL DEFAULT (UUID()),
    cpf VARCHAR(14) UNIQUE NOT NULL,
    nome VARCHAR(100) NOT NULL,
    matricula VARCHAR(20) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    id_departamento INT,
    id_curso INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (id_departamento) REFERENCES Departamento(id),
    FOREIGN KEY (id_curso) REFERENCES Curso(id),
);

CREATE TABLE Funcionario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    usuario_id INT UNIQUE NOT NULL,
    uuid CHAR(36) UNIQUE NOT NULL DEFAULT (UUID()),
    cpf VARCHAR(14) UNIQUE NOT NULL,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id) ON DELETE CASCADE,
);

CREATE TABLE Sala (
    id INT PRIMARY KEY AUTO_INCREMENT,
    uuid CHAR(36) UNIQUE NOT NULL DEFAULT (UUID()),
    numero VARCHAR(20) NOT NULL,
    departamento_id INT NOT NULL,
    capacidade INT,
    tipo ENUM('aula', 'laboratorio', 'auditorio', 'sala_reuniao') DEFAULT 'aula',
    tem_projetor BOOLEAN DEFAULT TRUE,
    tem_ar_condicionado BOOLEAN DEFAULT TRUE,
    tem_computadores BOOLEAN DEFAULT FALSE,
    status ENUM('ativa', 'manutencao', 'inativa') DEFAULT 'ativa',
    observacoes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (departamento_id) REFERENCES Departamento(id),
    UNIQUE KEY unique_sala_depto (numero, departamento_id)
);

CREATE TABLE Disciplina (
    id INT PRIMARY KEY AUTO_INCREMENT,
    uuid CHAR(36) UNIQUE NOT NULL DEFAULT (UUID()),
    codigo VARCHAR(20) UNIQUE NOT NULL,
    nome VARCHAR(150) NOT NULL,
    carga_horaria INT NOT NULL,
    departamento_id INT NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (departamento_id) REFERENCES Departamento(id),
);

CREATE TABLE Reserva (
    id INT PRIMARY KEY AUTO_INCREMENT,
    uuid CHAR(36) UNIQUE NOT NULL DEFAULT (UUID()),
    sala_id INT NOT NULL,
    professor_id INT,
    aluno_id INT,
    disciplina_id INT,
    data_reserva DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fim TIME NOT NULL, 
    tipo_reserva ENUM('aula', 'evento', 'reuniao', 'manutencao') DEFAULT 'aula',
    status ENUM('ativa', 'cancelada', 'concluida') DEFAULT 'ativa',
    observacoes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by INT,
    FOREIGN KEY (sala_id) REFERENCES Sala(id),
    FOREIGN KEY (professor_id) REFERENCES Professor(id),
    FOREIGN KEY (aluno_id) REFERENCES Aluno(id),
    FOREIGN KEY (disciplina_id) REFERENCES Disciplina(id),
    FOREIGN KEY (created_by) REFERENCES Funcionario(id)
    CONSTRAINT chk_horario_valido CHECK (hora_fim > hora_inicio),
    CONSTRAINT chk_um_responsavel CHECK (
        (professor_id IS NOT NULL AND aluno_id IS NULL) OR
        (professor_id IS NULL AND aluno_id IS NOT NULL)
    ),
);