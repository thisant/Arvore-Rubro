package br.com.arvore_rubro_negra;

public class Arvore {
	private No raiz;
	private No TNULL;

	public void inserir(long key) {
		No no = new No();
		no.pai = null;
		no.dados = key;
		no.esquerdo = TNULL;
		no.direito = TNULL;
		no.cor = 1;

		No b = null;
		No a = this.raiz;

		while (a != TNULL) {
			b = a;
			if (no.dados < a.dados) {
				a = a.esquerdo;
			} else {
				a = a.direito;
			}
		}

		no.pai = b;
		if (b == null) {
			raiz = no;
		} else if (no.dados < b.dados) {
			b.esquerdo = no;
		} else {
			b.direito = no;
		}

		if (no.pai == null) {
			no.cor = 0;
			return;
		}

		if (no.pai.pai == null) {
			return;
		}

		rotacionarInserir(no);
	}

	private void rotacionarInserir(No z) {
		No tio;
		while (z.pai.cor == 1) {
			if (z.pai == z.pai.pai.direito) {
				tio = z.pai.pai.esquerdo;
				if (tio.cor == 1) {
					tio.cor = 0;
					z.pai.cor = 0;
					z.pai.pai.cor = 1;
					z = z.pai.pai;
				} else {
					if (z == z.pai.esquerdo) {
						z = z.pai;
						direitoRotacao(z);
					}
					z.pai.cor = 0;
					z.pai.pai.cor = 1;
					esquerdoRotacao(z.pai.pai);
				}
			} else {
				tio = z.pai.pai.direito;

				if (tio.cor == 1) {
					tio.cor = 0;
					z.pai.cor = 0;
					z.pai.pai.cor = 1;
					z = z.pai.pai;
				} else {
					if (z == z.pai.direito) {
						z = z.pai;
						esquerdoRotacao(z);
					}

					z.pai.cor = 0;
					z.pai.pai.cor = 1;
					direitoRotacao(z.pai.pai);
				}
			}
			if (z == raiz) {
				break;
			}
		}
		raiz.cor = 0;
	}
	
	private void inorder(No no) {
		if (no != TNULL) {
			inorder(no.esquerdo);
			System.out.print(no.dados + " ");
			inorder(no.direito);
		}
	}

	private void postorder(No no) {
		if (no != TNULL) {
			postorder(no.esquerdo);
			postorder(no.direito);
			System.out.print(no.dados + " ");
		}
	}
	
	private void preorder(No no) {
		if (no != TNULL) {
			System.out.print(no.dados + " ");
			preorder(no.esquerdo);
			preorder(no.direito);
		}
	}
	
	private void remover(No no, long key) {
		No c = TNULL;
		No a, b;
		while (no != TNULL) {
			if (no.dados == key) {
				c = no;
			}

			if (no.dados <= key) {
				no = no.direito;
			} else {
				no = no.esquerdo;
			}
		}

		if (c == TNULL) {
			System.out.println("O nó não foi encontrado! ");
			return;
		}

		b = c;
		int coloracao = b.cor;
		if (c.esquerdo == TNULL) {
			a = c.direito;
			trocarNo(c, c.direito);
		} else if (c.direito == TNULL) {
			a = c.esquerdo;
			trocarNo(c, c.esquerdo);
		} else {
			b = minimo(c.direito);
			coloracao = b.cor;
			a = b.direito;
			if (b.pai == c) {
				a.pai = b;
			} else {
				trocarNo(b, b.direito);
				b.direito = c.direito;
				b.direito.pai = b;
			}

			trocarNo(c, b);
			b.esquerdo = c.esquerdo;
			b.esquerdo.pai = b;
			b.cor = c.cor;
		}
		System.out.println("O nó foi removido! ");
		if (coloracao == 0) {
			rotacionarRemover(a);
		}
	}

	private void rotacionarRemover(No a) {
		No parente;
		while (a != raiz && a.cor == 0) {
			if (a == a.pai.esquerdo) {
				parente = a.pai.direito;
				if (parente.cor == 1) {
					parente.cor = 0;
					a.pai.cor = 1;
					esquerdoRotacao(a.pai);
					parente = a.pai.direito;
				}

				if (parente.esquerdo.cor == 0 && parente.direito.cor == 0) {
					parente.cor = 1;
					a = a.pai;
				} else {
					if (parente.direito.cor == 0) {
						parente.esquerdo.cor = 0;
						parente.cor = 1;
						direitoRotacao(parente);
						parente = a.pai.direito;
					}

					parente.cor = a.pai.cor;
					a.pai.cor = 0;
					parente.direito.cor = 0;
					esquerdoRotacao(a.pai);
					a = raiz;
				}
			} else {
				parente = a.pai.esquerdo;
				if (parente.cor == 1) {
					parente.cor = 0;
					a.pai.cor = 1;
					direitoRotacao(a.pai);
					parente = a.pai.esquerdo;
				}

				if (parente.direito.cor == 0 && parente.direito.cor == 0) {
					parente.cor = 1;
					a = a.pai;
				} else {
					if (parente.esquerdo.cor == 0) {
						parente.direito.cor = 0;
						parente.cor = 1;
						esquerdoRotacao(parente);
						parente = a.pai.esquerdo;
					}

					parente.cor = a.pai.cor;
					a.pai.cor = 0;
					parente.esquerdo.cor = 0;
					direitoRotacao(a.pai);
					a = raiz;
				}
			}
		}
		a.cor = 0;
	}

	private No buscarno(No no, long key) {
		if (no == TNULL || key == no.dados) {
			return no;
		}

		if (key < no.dados) {
			return buscarno(no.esquerdo, key);
		}
		return buscarno(no.direito, key);
	}

	private void trocarNo(No u, No v) {
		if (u.pai == null) {
			raiz = v;
		} else if (u == u.pai.esquerdo) {
			u.pai.esquerdo = v;
		} else {
			u.pai.direito = v;
		}
		v.pai = u.pai;
	}

	private void imprimir(No raiz, String indent, boolean last) {
		if (raiz != TNULL) {
			System.out.print(indent);
			if (last) {
				System.out.print("Direito: ");
				indent += "";
			} else {
				System.out.print("Esquerdo: ");
				indent += "";
			}

			String parenteCor = raiz.cor == 1 ? "Vermelho" : "Preto";
			System.out.println(raiz.dados + " -" + parenteCor);
			imprimir(raiz.esquerdo, indent, false);
			imprimir(raiz.direito, indent, true);
		}
	}

	public Arvore() {
		TNULL = new No();
		raiz = TNULL;
		TNULL.esquerdo = null;
		TNULL.direito = null;
		TNULL.cor = 0;
	}
	
	public No maximo(No no) {
		while (no.direito != TNULL) {
			no = no.direito;
		}
		return no;
	}
	
	public No minimo(No no) {
		while (no.esquerdo != TNULL) {
			no = no.esquerdo;
		}
		return no;
	}

	public void preOrder() {
		preorder(this.raiz);
	}

	public void inOrder() {
		inorder(this.raiz);
	}

	public void postOrder() {
		postorder(this.raiz);
	}

	public No buscarNo(long k) {
		return buscarno(this.raiz, k);
	}
	
	public No getRaiz() {
		return this.raiz;
	}
	
	public No predecessor(No a) {
		if (a.esquerdo != TNULL) {
			return maximo(a.esquerdo);
		}

		No b = a.pai;
		while (b != TNULL && a == b.esquerdo) {
			a = b;
			b = b.pai;
		}

		return b;
	}

	public No successor(No a) {
		if (a.direito != TNULL) {
			return minimo(a.direito);
		}

		No b = a.pai;
		while (b != TNULL && a == b.direito) {
			a = b;
			b = b.pai;
		}
		return b;
	}
	
	public void removerNo(long dados) {
		remover(this.raiz, dados);
	}
	
	public void direitoRotacao(No a) {
		No b = a.esquerdo;
		a.esquerdo = b.direito;
		if (b.direito != TNULL) {
			b.direito.pai = a;
		}
		b.pai = a.pai;
		if (a.pai == null) {
			this.raiz = b;
		} else if (a == a.pai.direito) {
			a.pai.direito = b;
		} else {
			a.pai.esquerdo = b;
		}
		b.direito = a;
		a.pai = b;
	}
	
	public void esquerdoRotacao(No a) {
		No b = a.direito;
		a.direito = b.esquerdo;
		if (b.esquerdo != TNULL) {
			b.esquerdo.pai = a;
		}
		b.pai = a.pai;
		if (a.pai == null) {
			this.raiz = b;
		} else if (a == a.pai.esquerdo) {
			a.pai.esquerdo = b;
		} else {
			a.pai.direito = b;
		}
		b.esquerdo = a;
		a.pai = b;
	}

	public void Impressao() {
		imprimir(this.raiz, "", true);
	}
}