//
// Created by mxz on 2022/12/22.
//

#include<iostream>
#include <list>

using namespace std;

class Node;

class Dom;

class NodeCollect {
private:
    Node *n;
    int length;
public:
    NodeCollect();

    ~NodeCollect();

    int getLength() const;

    Node *item(int i);

    void add(Node *nn);

};

class Node {
private:
    int start;
    int len;
public:
    char *innerHtml(Dom &d) const;

    char *outerHtml(Dom &d) const;

    char *innerText(Dom &d) const;

    char *getAttr(Dom &d, char *str) const;

    char *tagName(Dom &d) const;

    Node *getParent(Dom &d) const;

    NodeCollect *getChild(Dom &d) const;

    Node *getNext(Dom &d) const;

    Node *getPrevious(Dom &d) const;

    Node *next;

    void setStart(int i);

    void setLen(int i);

    int getStart() const;

    int getLen() const;
};

/**
 * 整个文件
 */
class Dom {
private:
    char *text;
    Node *n;
    int count;

    int parse(char *s);

public:
    ~Dom();

    char *getText();

    void load(char *str);

    Node *getItem(int i);

    int getCount();

    Node *getById(char *id);

    NodeCollect *getByTagName(char *tagName);
};

/**
 * 初始化
 *
 * @param str
 */
void Dom::load(char *str) {
    n = nullptr;
    count = 0;
    text = new char[strlen(str) + 1];
    strcpy(text, str);
    char *t = text;
    parse(t);
}

int Dom::getCount() {
    return count;
}

char *Dom::getText() {
    return text;
}

Node *Dom::getItem(int i) {
    Node *n1 = n;
    while (i--) {
        if (n1) {
            n1 = n1->next;
        } else {
            return nullptr;
        }
    }
    return n1;
}

Node *Dom::getById(char *id) {
    for (int i = 0; i < this->getCount(); i++) {
        if (strcmp(this->getItem(i)->getAttr(*this, "id"), id) == 0) {
            return this->getItem(i);
        }
    }
    return nullptr;
}

NodeCollect *Dom::getByTagName(char *tagName) {
    auto *nNode = new NodeCollect;
    for (int i = 0; i < this->getCount(); i++) {
        if (strcmp(this->getItem(i)->tagName(*this), tagName) == 0) {
            nNode->add(this->getItem(i));
        }
    }
    return nNode;
}

Dom::~Dom() {
    delete[] text;
    Node *n1 = n, *n2;
    if (n1) {
        while (n1->next != nullptr) {
            n2 = n1;
            n1 = n1->next;
            delete n2;
        }
    }
}

/**
 * 解析
 *
 * @param s
 * @return
 */
int Dom::parse(char *s) {
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
                Node *nn = new Node;
                nn->setStart(s - text);
                nn->setLen(0);
                nn->next = nullptr;

                if (n) {
                    Node *n1 = n;
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
                Node *nn = new Node;
                nn->setStart(s - text);
                nn->setLen(0);
                nn->next = nullptr;

                if (n) {
                    Node *n1 = n;
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
                Node *n1 = n;
                while (n1->next != 0) {
                    n1 = n1->next;
                }
                n1->setLen((long) s + 2 - (n1->getStart()) - (long) text);
                if (i4 == 1) {
                    i4 = 0;
                }
            }
        }

        if (*s == '<' && *(s + 1) == '/') {
            if (i1 == 0 && i2 == 0) {
                i3 = 0;
                i4 = 0;
                Node *n1 = n;
                Node *min;
                int i = 0;
                while (n1 != nullptr) {
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

void Node::setStart(int i) {
    start = i;
}

void Node::setLen(int i) {
    len = i;
}

int Node::getStart() const {
    return start;
}

int Node::getLen() const {
    return len;
}

char *Node::getAttr(Dom &d, char *str) const {
    char *out = outerHtml(d);
    int i1 = 0, i2 = 0;
    char *v = new char[strlen(out) + 1];
    memset(v, 0, strlen(out) + 1);
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

Node *Node::getParent(Dom &d) const {
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

NodeCollect *Node::getChild(Dom &d) const {
    int p = -1;
    auto *nn = new NodeCollect;
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

char *Node::outerHtml(Dom &d) const {
    char *out = new char[len + 1];
    char *c = d.getText() + start;
    for (int i = 0; i < len; i++) {
        *(out + i) = *(c + i);
    }
    out[len] = 0;
    return out;
}

char *Node::tagName(Dom &d) const {
    char *out = this->outerHtml(d);
    char *tag = new char[strlen(out) + 1];
    int i = 1;
    for (; *(out + i) != ' ' && *(out + i) != '-' && *(out + i) != '/' && *(out + i) != '>'; i++) {
        tag[i - 1] = *(out + i);
    }
    tag[i - 1] = 0;
    return tag;
}

char *Node::innerHtml(Dom &d) const {
    char *out = outerHtml(d);
    char *base = out;
    int l = strlen(out);
    int i1 = 0, i2 = 0;
    char *inner = new char[strlen(out) + 1];
    memset(inner, 0, strlen(out) + 1);
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
    int innerLen = l - (strlen(tagName(d)) + 3) - (out - base + 1);
    if (innerLen == 0) {
        return inner;
    } else {
        for (int i = 0; i < innerLen; i++) {
            inner[i] = *(out + i + 1);
        }
        return inner;
    }
}

/**
 * 获取标签内容
 *
 * @param d
 * @return
 */
char *Node::innerText(Dom &d) const {
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
        memset(inner, 0, strlen(h) + 1);
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
            *(inner + i) = *h;
            i++;
        }


    }
    return inner;
}

Node *Node::getPrevious(Dom &d) const {
    Node *nn = nullptr;
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

Node *Node::getNext(Dom &d) const {
    Node *nn = nullptr;
    for (int i = 0; i < d.getCount(); i++) {
        if (start + len <= d.getItem(i)->getStart()) {
            nn = d.getItem(i);
            break;
        }
    }
    return nn;
}

NodeCollect::NodeCollect() {
    n = nullptr;
    length = 0;
}

int NodeCollect::getLength() const {
    return length;
}

Node *NodeCollect::item(int i) {
    Node *n1 = n;
    while (i--) {
        if (n1) {
            n1 = n1->next;
        } else {
            return nullptr;
        }
    }
    return n1;
}

void NodeCollect::add(Node *nn) {
    Node *n1 = new Node;
    n1->setStart(nn->getStart());
    n1->setLen(nn->getLen());
    n1->next = 0;
    if (n) {
        Node *n2 = n;
        while (n2->next) {
            n2 = n2->next;
        }
        n2->next = n1;

    } else {
        n = n1;

    }
    length++;
}

NodeCollect::~NodeCollect() {
    Node *n1 = n, *n2;
    if (n1) {
        while (n1->next != 0) {
            n2 = n1;
            n1 = n1->next;
            delete n2;
        }
    }
}

int main() {
    Dom a{};
    cout << "=========== START ===========" << endl;
    a.load("<body><a href=\"https://www.163.com/#f=topnav\" class=\"ntes-nav-select-title ntes-nav-entry-bgblack JS_NTES_LOG_FE\">应用<em class=\"ntes-nav-select-arr\"></em></a></body>");
    cout << "标签内容: " << a.getByTagName("a")->item(0)->innerText(a) << endl;
    cout << "标签个数: " << a.getCount() << endl;
    cout << "END" << endl;
    cout << "=========== END ===========" << endl;
    return 0;
}