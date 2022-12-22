//
// Created by mxz on 2022/12/22.
//

#include<iostream>
#include <list>

using namespace std;

class node;

class dom;

class nodecollect {
private:
    node *n;
    int length;
public:
    nodecollect();

    ~nodecollect();

    int getLength();

    node *item(int i);

    void add(node *nn);

};

class node {
private:
    int start;
    int len;
public:
    char *innerHtml(dom &d) const;

    char *outerHtml(dom &d) const;

    char *innerText(dom &d) const;

    char *getattr(dom &d, char *str);

    char *tagName(dom &d) const;

    node *getParent(dom &d) const;

    nodecollect *getChild(dom &d) const;

    node *getNext(dom &d);

    node *getPrevious(dom &d);

    node *next;

    void setStart(int i);

    void setLen(int i);

    int getStart();

    int getLen();
};

class dom {
private:
    char *text;
    node *n;
    int count;

    int parse(char *s);

public:
    ~dom();

    char *getText();

    void load(char *str);

    node *getItem(int i);

    int getCount();

    node *getById(char *id);

    nodecollect *getByTagName(char *tagName);
};

void dom::load(char *str) {
    n = nullptr;
    count = 0;
    int l = strlen(str);
    text = new char[l + 1];
    strcpy(text, str);
    char *t = text;
    parse(t);
}

int dom::getCount() {
    return count;
}

char *dom::getText() {
    return text;
}

node *dom::getItem(int i) {
    node *n1 = n;
    while (i--) {
        if (n1) {
            n1 = n1->next;
        } else {
            return nullptr;
        }
    }
    return n1;
}

node *dom::getById(char *id) {
    for (int i = 0; i < this->getCount(); i++) {
        if (strcmp(this->getItem(i)->getattr(*this, "id"), id) == 0) {
            return this->getItem(i);
        }
    }
    return nullptr;
}

nodecollect *dom::getByTagName(char *tagName) {
    auto *nnode = new nodecollect;
    for (int i = 0; i < this->getCount(); i++) {
        if (strcmp(this->getItem(i)->tagName(*this), tagName) == 0) {
            nnode->add(this->getItem(i));
        }
    }
    return nnode;
}

dom::~dom() {
    delete[] text;
    node *n1 = n, *n2;
    if (n1) {
        while (n1->next != nullptr) {
            n2 = n1;
            n1 = n1->next;
            delete n2;
        }
    }
}

int dom::parse(char *s) {
    int i1 = 0, i2 = 0, i3 = 0, i4 = 0;
    while (*s != 0) {
        if (*s == 0) {
            return (long) s;
        }
        if (i3 == 1 || i4 == 1) {
            if (*s == '\"' && *(s - 1) != '\\') {
                if (i1 == 0) {
                    i1 = 1;
                } else {
                    i1 = 0;
                }
            }
            if (*s == '\'' && *(s - 1) != '\\') {
                if (i2 == 0) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
            }
        }
        if (*s == '<' && *(s + 1) == '!') {
            if (i1 == 0 && i2 == 0) {
                i3 = 1;
                node *nn = new node;
                nn->setStart(s - text);
                nn->setLen(0);
                nn->next = nullptr;

                if (n) {
                    node *n1 = n;
                    while (n1->next != 0) {
                        n1 = n1->next;
                    }
                    n1->next = nn;
                } else {
                    n = nn;
                }
                int s1 = (long) s;
                while (*s) {
                    if (*s == '\"') {
                        if (i1 == 0) {
                            i1 = 1;
                        } else {
                            i1 = 0;
                        }
                    }
                    if (*s == '\'') {
                        if (i2 == 0) {
                            i2 = 1;
                        } else {
                            i2 = 0;
                        }
                    }
                    if (*s == '>') {
                        if (i1 == 0 && i2 == 0) {
                            // cout<<(long)s+1-s1<<endl;
                            nn->setLen((long) s + 1 - s1);
                            s++;
                            break;
                        }

                    }
                    s++;
                }
                count++;
            }
        }
        if (*s == '<' && *(s + 1) != '/' && *(s + 1) != '!') {

            if (i1 == 0 && i2 == 0 && i4 == 0) {
                i3 = 1;
                node *nn = new node;
                //cout<<s-text<<endl;
                nn->setStart(s - text);
                nn->setLen(0);
                nn->next = nullptr;

                if (n) {
                    node *n1 = n;
                    while (n1->next != 0) {
                        n1 = n1->next;
                    }
                    n1->next = nn;
                } else {
                    n = nn;
                }
                if ((*(s + 1) == 's' || *(s + 1) == 'S') && (*(s + 2) == 'c' || *(s + 2) == 'C') &&
                    (*(s + 3) == 'r' || *(s + 3) == 'R') && (*(s + 4) == 'i' || *(s + 4) == 'I') &&
                    (*(s + 5) == 'p' || *(s + 5) == 'P') && (*(s + 6) == 't' || *(s + 6) == 'T')) {
                    i4 = 1;
                }
                count++;
            }
        }
        if (*s == '>') {
            if (i1 == 0 && i2 == 0) {
                i3 = 0;
            }
        }
        if (*s == '/' && *(s + 1) == '>') {
            if (i1 == 0 && i2 == 0) {
                i3 = 0;
                node *n1 = n;
                while (n1->next != 0) {
                    n1 = n1->next;
                }
                // cout<<(long)s+2-(n1->getStart())-(long)text<<endl;
                n1->setLen((long) s + 2 - (n1->getStart()) - (long) text);
                if (i4 == 1) {
                    i4 = 0;
                }
            }
        }

        if (*s == '<' && *(s + 1) == '/') {
            if (i1 == 0 && i2 == 0) {
                i3 = 0;
                if (i4 = 1) {
                    i4 = 0;
                }
                node *n1 = n;
                node *min;
                int i = 0;
                while (n1 != 0) {
                    if (n1->getLen() == 0) {
                        min = n1;
                    }
                    n1 = n1->next;
                    i++;
                }
                n1 = min;
                while (*s != '>') {
                    s++;
                }
                n1->setLen((long) s + 1 - (n1->getStart()) - (long) text);
            }
        }
        s++;
    }
    return 0;
}

void node::setStart(int i) {
    start = i;
}

void node::setLen(int i) {
    len = i;
}

int node::getStart() {
    return start;
}

int node::getLen() {
    return len;
}

char *node::getattr(dom &d, char *str) {
    char *out = outerHtml(d);
    int i1 = 0, i2 = 0;
    char *v = new char[strlen(out) + 1];
    ::memset(v, 0, strlen(out) + 1);
    while (*out != 0) {

        if (*out == 0) {
            return v;
        }
        if (*out == '\"') {
            if (i1 == 0) {
                i1 = 1;
            } else {
                i1 = 0;
            }
        }
        if (*out == '\'') {
            if (i2 == 0) {
                i2 = 1;
            } else {
                i2 = 0;
            }
        }
        if (*out == '>') {
            if (i1 == 0 && i2 == 0) {
                return v;
            }
        }
        char *s = strstr(out, str);
        if (s != nullptr && s - out == 0) {
            if (i1 == 0 && i2 == 0) {
                if (*(s - 1) == ' ' && *(s + strlen(str)) == '=' &&
                    (*(s + strlen(str) + 1) == '\'' || *(s + strlen(str) + 1) == '\"')) {
                    char f = *(s + strlen(str) + 1);
                    char *t = s + strlen(str) + 2;
                    int ii = 0;
                    while (*t != f || *(t - 1) == '\\') {
                        v[ii] = *t;
                        t++;
                        ii++;
                    }
                    return v;
                }
            }

        }
        out++;
    }
    return nullptr;
}

node *node::getParent(dom &d) const {
    int p = -1;
    for (int i = 0; i < d.getCount(); i++) {
        if (d.getItem(i)->getStart() < start) {
            if (d.getItem(i)->getLen() + d.getItem(i)->getStart() > start + len) {
                p = i;
            }
        } else {
            break;
        }
    }
    if (p == -1) {
        return nullptr;
    } else {
        return d.getItem(p);
    }
}

nodecollect *node::getChild(dom &d) const {
    int p = -1;
    auto *nn = new nodecollect;
    for (int i = 0; i < d.getCount(); i++) {
        if (d.getItem(i)->getStart() > start) {
            p = i;
            break;
        }
    }
    if (p != -1) {
        for (; p < d.getCount(); p++) {
            if (start + len > d.getItem(p)->getLen() + d.getItem(p)->getStart()) {
                nn->add(d.getItem(p));
            } else {
                break;
            }
        }

    }
    return nn;
}

char *node::outerHtml(dom &d) const {
    char *out = new char[len + 1];
    char *c = d.getText() + start;
    for (int i = 0; i < len; i++) {
        *(out + i) = *(c + i);
    }
    out[len] = 0;
    return out;
}

char *node::tagName(dom &d) const {
    char *out = this->outerHtml(d);
    char *tag = new char[strlen(out) + 1];
    int i = 1;
    for (; *(out + i) != ' ' && *(out + i) != '-' && *(out + i) != '/' && *(out + i) != '>'; i++) {
        tag[i - 1] = *(out + i);
    }
    tag[i - 1] = 0;
    return tag;
}

char *node::innerHtml(dom &d) const {
    char *out = outerHtml(d);
    char *base = out;
    int l = strlen(out);
    int i1 = 0, i2 = 0;
    char *inner = new char[strlen(out) + 1];
    ::memset(inner, 0, strlen(out) + 1);
    while (*out != 0) {

        if (*out == 0) {
            return inner;
        }
        if (*out == '\"') {
            if (i1 == 0) {
                i1 = 1;
            } else {
                i1 = 0;
            }
        }
        if (*out == '\'') {
            if (i2 == 0) {
                i2 = 1;
            } else {
                i2 = 0;
            }
        }
        if (*out == '>') {
            if (i1 == 0 && i2 == 0) {
                break;
            }
        }
        out++;
    }
    int innerlen = l - (strlen(tagName(d)) + 3) - (out - base + 1);
    if (innerlen == 0) {
        return inner;
    } else {
        for (int i = 0; i < innerlen; i++) {
            inner[i] = *(out + i + 1);
        }
        return inner;
    }
}

char *node::innerText(dom &d) const {
    char *h = innerHtml(d);
    char *inner;
    if (h[0] == 0) {
        inner = new char;
        *inner = 0;
        return inner;
    } else if (strcmp(this->tagName(d), "script") == 0 || strcmp(this->tagName(d), "SCRIPT") == 0) {
        inner = new char[strlen(h) + 1];
        strcpy(inner, h);
        return inner;
    } else {
        inner = new char[strlen(h) + 1];
        ::memset(inner, 0, strlen(h) + 1);
    }
    int i = 0, i1 = 0, i2 = 0, i3 = 0;
    for (; *h != 0; h++) {

        if (*h == 0) {
            return inner;
        }
        if (*h == '<') {
            if (i3 == 0) {
                i3 = 1;

            }
        }
        if (i3 == 1) {
            if (*h == '\"') {
                if (i1 == 0) {
                    i1 = 1;
                } else {
                    i1 = 0;
                }

            }
            if (*h == '\'') {
                if (i2 == 0) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }

            }
            if (*h == '>') {
                if (i1 == 0 && i2 == 0) {
                    i3 = 0;

                }
            }
        } else {
            //cout<<*h;
            *(inner + i) = *h;
            i++;
        }


    }
    return inner;
}

node *node::getPrevious(dom &d) {
    node *nn = 0;
    for (int i = 0; i < d.getCount(); i++) {
        if (d.getItem(i)->getStart() == start && d.getItem(i)->getLen() == len) {
            break;
        } else {
            if (start >= d.getItem(i)->getStart() + d.getItem(i)->getLen()) {
                nn = d.getItem(i);
            }
        }
    }
    return nn;
}

node *node::getNext(dom &d) {
    node *nn = 0;
    for (int i = 0; i < d.getCount(); i++) {
        if (start + len <= d.getItem(i)->getStart()) {
            nn = d.getItem(i);
            break;
        }
    }
    return nn;
}

nodecollect::nodecollect() {
    n = nullptr;
    length = 0;
}

int nodecollect::getLength() {
    return length;
}

node *nodecollect::item(int i) {
    node *n1 = n;
    while (i--) {
        if (n1) {
            n1 = n1->next;
        } else {
            return 0;
        }
    }
    return n1;
}

void nodecollect::add(node *nn) {
    node *n1 = new node;
    n1->setStart(nn->getStart());
    n1->setLen(nn->getLen());
    n1->next = 0;
    if (n) {
        node *n2 = n;
        while (n2->next) {
            n2 = n2->next;
        }
        n2->next = n1;

    } else {
        n = n1;

    }
    length++;
}

nodecollect::~nodecollect() {
    node *n1 = n, *n2;
    if (n1) {
        while (n1->next != 0) {
            n2 = n1;
            n1 = n1->next;
            delete n2;
        }
    }
}

int main() {
    dom a{};
    cout << "START" << endl;
    a.load("<body><script>document.write('</a>sada</a>')</script></body>");
    cout << a.getByTagName("script")->item(0)->innerText(a) << endl;
    cout << a.getCount() << endl;
    cout << a.getText() << endl;
    cout << "END" << endl;
    return 0;
}