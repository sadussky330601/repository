
#ifndef _INCLUDE_MALLOC_ALIGN
#define _INCLUDE_MALLOC_ALIGN

#ifndef INTERNAL_SIZE_T
#define INTERNAL_SIZE_T size_t
#endif
#define SIZE_SZ                (sizeof(INTERNAL_SIZE_T))
#ifndef MALLOC_ALIGNMENT
#define MALLOC_ALIGNMENT       (2 * SIZE_SZ)
#endif
#define MALLOC_ALIGN_MASK      (MALLOC_ALIGNMENT - 1)
#define MIN_CHUNK_SIZE        (sizeof(char ))
#define MINSIZE \
(unsigned long)(((MIN_CHUNK_SIZE+MALLOC_ALIGN_MASK) & ~MALLOC_ALIGN_MASK))
/* pad request bytes into a usable size -- internal version */
#define request2size(req)                                        \
		(((req) + SIZE_SZ + MALLOC_ALIGN_MASK < MINSIZE) ? MINSIZE : \
		((req) + SIZE_SZ + MALLOC_ALIGN_MASK) & ~MALLOC_ALIGN_MASK)
/***************glibc End ************************/
#endif
