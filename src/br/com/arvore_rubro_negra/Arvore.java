package br.com.arvore_rubro_negra;

public class Arvore {
	private No raiz;
	private No TNULL;

	private void preorder(No no) {
		if (no != TNULL) {
			System.out.print(no.dados + " ");
			preorder(no.esquerdo);
			preorder(no.direito);
		}
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

	public void inserir(long key) {
		No no = new No();
		no.pai = null;
		no.dados = key;
		no.esquerdo = TNULL;
		no.direito = TNULL;
		no.cor = 1;

		No y = null;
		No x = this.raiz;

		while (x != TNULL) {
			y = x;
			if (no.dados < x.dados) {
				x = x.esquerdo;
			} else {
				x = x.direito;
			}
		}

		no.pai = y;
		if (y == null) {
			raiz = no;
		} else if (no.dados < y.dados) {
			y.esquerdo = no;
		} else {
			y.direito = no;
		}

		if (no.pai == null) {
			no.cor = 0;
			return;
		}

		if (no.pai.pai == null) {
			return;
		}

		corrigirInserir(no);
	}

	private void corrigirInserir(No k) {
		No u;
		while (k.pai.cor == 1) {
			if (k.pai == k.pai.pai.direito) {
				u = k.pai.pai.esquerdo;
				if (u.cor == 1) {
					u.cor = 0;
					k.pai.cor = 0;
					k.pai.pai.cor = 1;
					k = k.pai.pai;
				} else {
					if (k == k.pai.esquerdo) {
						k = k.pai;
						direitoRotacao(k);
					}
					k.pai.cor = 0;
					k.pai.pai.cor = 1;
					esquerdoRotacao(k.pai.pai);
				}
			} else {
				u = k.pai.pai.direito;

				if (u.cor == 1) {
					u.cor = 0;
					k.pai.cor = 0;
					k.pai.pai.cor = 1;
					k = k.pai.pai;
				} else {
					if (k == k.pai.direito) {
						k = k.pai;
						esquerdoRotacao(k);
					}

					k.pai.cor = 0;
					k.pai.pai.cor = 1;
					direitoRotacao(k.pai.pai);
				}
			}
			if (k == raiz) {
				break;
			}
		}
		raiz.cor = 0;
	}

	private void remover(No no, long key) {
		No z = TNULL;
		No x, y;
		while (no != TNULL) {
			if (no.dados == key) {
				z = no;
			}

			if (no.dados <= key) {
				no = no.direito;
			} else {
				no = no.esquerdo;
			}
		}

		if (z == TNULL) {
			System.out.println("O nó não foi encontrado! ");
			return;
		}

		y = z;
		int yOriginalcor = y.cor;
		if (z.esquerdo == TNULL) {
			x = z.direito;
			trocarNo(z, z.direito);
		} else if (z.direito == TNULL) {
			x = z.esquerdo;
			trocarNo(z, z.esquerdo);
		} else {
			y = minimo(z.direito);
			yOriginalcor = y.cor;
			x = y.direito;
			if (y.pai == z) {
				x.pai = y;
			} else {
				trocarNo(y, y.direito);
				y.direito = z.direito;
				y.direito.pai = y;
			}

			trocarNo(z, y);
			y.esquerdo = z.esquerdo;
			y.esquerdo.pai = y;
			y.cor = z.cor;
		}
		System.out.println("O nó foi encontrado e removido! ");
		if (yOriginalcor == 0) {
			corrigirRemover(x);
		}
	}

	private void corrigirRemover(No x) {
		No s;
		while (x != raiz && x.cor == 0) {
			if (x == x.pai.esquerdo) {
				s = x.pai.direito;
				if (s.cor == 1) {
					s.cor = 0;
					x.pai.cor = 1;
					esquerdoRotacao(x.pai);
					s = x.pai.direito;
				}

				if (s.esquerdo.cor == 0 && s.direito.cor == 0) {
					s.cor = 1;
					x = x.pai;
				} else {
					if (s.direito.cor == 0) {
						s.esquerdo.cor = 0;
						s.cor = 1;
						direitoRotacao(s);
						s = x.pai.direito;
					}

					s.cor = x.pai.cor;
					x.pai.cor = 0;
					s.direito.cor = 0;
					esquerdoRotacao(x.pai);
					x = raiz;
				}
			} else {
				s = x.pai.esquerdo;
				if (s.cor == 1) {
					s.cor = 0;
					x.pai.cor = 1;
					direitoRotacao(x.pai);
					s = x.pai.esquerdo;
				}

				if (s.direito.cor == 0 && s.direito.cor == 0) {
					s.cor = 1;
					x = x.pai;
				} else {
					if (s.esquerdo.cor == 0) {
						s.direito.cor = 0;
						s.cor = 1;
						esquerdoRotacao(s);
						s = x.pai.esquerdo;
					}

					s.cor = x.pai.cor;
					x.pai.cor = 0;
					s.esquerdo.cor = 0;
					direitoRotacao(x.pai);
					x = raiz;
				}
			}
		}
		x.cor = 0;
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
				System.out.print("Direito-");
				indent += "     ";
			} else {
				System.out.print("Esquerdo-");
				indent += "|    ";
			}

			String scor = raiz.cor == 1 ? "Vermelho" : "Preto";
			System.out.println(raiz.dados + "(" + scor + ")");
			imprimir(raiz.esquerdo, indent, false);
			imprimir(raiz.direito, indent, true);
		}
	}

	public Arvore() {
		TNULL = new No();
		TNULL.cor = 0;
		TNULL.esquerdo = null;
		TNULL.direito = null;
		raiz = TNULL;
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

	public No minimo(No no) {
		while (no.esquerdo != TNULL) {
			no = no.esquerdo;
		}
		return no;
	}

	public No maximo(No no) {
		while (no.direito != TNULL) {
			no = no.direito;
		}
		return no;
	}

	public No successor(No x) {
		if (x.direito != TNULL) {
			return minimo(x.direito);
		}

		No y = x.pai;
		while (y != TNULL && x == y.direito) {
			x = y;
			y = y.pai;
		}
		return y;
	}

	public No predecessor(No x) {
		if (x.esquerdo != TNULL) {
			return maximo(x.esquerdo);
		}

		No y = x.pai;
		while (y != TNULL && x == y.esquerdo) {
			x = y;
			y = y.pai;
		}

		return y;
	}

	public void esquerdoRotacao(No x) {
		No y = x.direito;
		x.direito = y.esquerdo;
		if (y.esquerdo != TNULL) {
			y.esquerdo.pai = x;
		}
		y.pai = x.pai;
		if (x.pai == null) {
			this.raiz = y;
		} else if (x == x.pai.esquerdo) {
			x.pai.esquerdo = y;
		} else {
			x.pai.direito = y;
		}
		y.esquerdo = x;
		x.pai = y;
	}

	public void direitoRotacao(No x) {
		No y = x.esquerdo;
		x.esquerdo = y.direito;
		if (y.direito != TNULL) {
			y.direito.pai = x;
		}
		y.pai = x.pai;
		if (x.pai == null) {
			this.raiz = y;
		} else if (x == x.pai.direito) {
			x.pai.direito = y;
		} else {
			x.pai.esquerdo = y;
		}
		y.direito = x;
		x.pai = y;
	}

	public No getRaiz() {
		return this.raiz;
	}

	public void removerNo(long dados) {
		remover(this.raiz, dados);
	}

	public void Impressao() {
		imprimir(this.raiz, "", true);
	}
}