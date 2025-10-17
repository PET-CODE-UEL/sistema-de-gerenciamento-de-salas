CREATE TABLE Funcionario ( /* AKA secretario (cara que vai agendar) */
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    telefone VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE Professor (
    id INT PRIMARY KEY AUTO_INCREMENT,
    matricula VARCHAR(20) UNIQUE NOT NULL,
    departamento VARCHAR(100),
    codigo_departamento INT,
    FOREIGN KEY (codigo_departamento) REFERENCES Departamento(codigo)
);

CREATE TABLE Departamento ( /* nao sei ao certo se um professor pertence a somente um departamento */
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    codigo VARCHAR(10) UNIQUE,
    descricao TEXT,
    chefe_id INT,
    FOREIGN KEY (chefe_id) REFERENCES Professor(id) /* acredito que todo chefe seja professor, por isso coloquei aqui */ 
);

/* se o numero de cada sala no departamento for unico, entao remover isso aqui. 
 		porem, se for unico por Edificio, dai sim manter.  */
CREATE TABLE Edificio (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    codigo VARCHAR(10) UNIQUE,
    endereco TEXT,
    numero_andares INT DEFAULT 1
);

/* talvez colocar numero do departamento */
CREATE TABLE Sala (
    id INT PRIMARY KEY AUTO_INCREMENT,
    numero VARCHAR(20) NOT NULL,
    /*edificio_id INT NOT NULL, */
    andar INT DEFAULT 1,
    capacidade INT,
    tipo ENUM('aula', 'laboratorio', 'auditorio', 'sala_reuniao', 'escritorio') DEFAULT 'aula',
    tem_projetor BOOLEAN DEFAULT FALSE,
    tem_ar_condicionado BOOLEAN DEFAULT FALSE,
    tem_computadores BOOLEAN DEFAULT FALSE,
    status ENUM('ativa', 'manutencao', 'inativa') DEFAULT 'ativa',
    observacoes TEXT,
    /*FOREIGN KEY (edificio_id) REFERENCES Edificio(id),*/
    UNIQUE KEY unique_sala_edificio (numero, edificio_id)
);

/* acho que esta ok */
CREATE TABLE Disciplina (
    id INT PRIMARY KEY AUTO_INCREMENT,
    codigo VARCHAR(20) UNIQUE NOT NULL,
    nome VARCHAR(150) NOT NULL,
    carga_horaria INT,
    departamento_id INT,
    FOREIGN KEY (departamento_id) REFERENCES Departamento(id)
);

/* acho que esta ok */
CREATE TABLE Reserva (
    id INT PRIMARY KEY AUTO_INCREMENT,
    sala_id INT NOT NULL,
    professor_id INT NOT NULL,
    disciplina_id INT,
    hora_inicio TIME NOT NULL,
    hora_fim TIME NOT NULL,
    tipo_reserva ENUM('aula', 'evento', 'reuniao', 'manutencao') DEFAULT 'aula',
    status ENUM('ativa', 'cancelada', 'concluida') DEFAULT 'ativa',
    observacoes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    FOREIGN KEY (sala_id) REFERENCES Sala(id),
    FOREIGN KEY (professor_id) REFERENCES Professor(id),
    FOREIGN KEY (disciplina_id) REFERENCES Disciplina(id),
    FOREIGN KEY (created_by) REFERENCES Funcionario(id)
);
