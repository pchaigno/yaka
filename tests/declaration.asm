; entete
extrn lirent:proc, ecrent:proc
extrn ecrbool:proc
extrn ecrch:proc, ligsuiv:proc
.model SMALL
.586

.CODE
debut :
STARTUPCODE

main :
; ouvbloc 8
enter 8, 0

; iconst 5
push word ptr 5

; istore -2
pop ax
mov word ptr [bp-2], ax

; queue
nop
EXITCODE
end debut